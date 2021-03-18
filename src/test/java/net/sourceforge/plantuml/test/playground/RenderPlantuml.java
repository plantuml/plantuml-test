package net.sourceforge.plantuml.test.playground;

import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.error.PSystemError;
import net.sourceforge.plantuml.preproc.Defines;
import net.sourceforge.plantuml.security.SFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import static net.sourceforge.plantuml.test.CachingResourceLoader.loadResourceAsString;
import static net.sourceforge.plantuml.test.playground.Data.calculateOutputFile;
import static net.sourceforge.plantuml.test.playground.Logging.log;
import static net.sourceforge.plantuml.test.playground.Logging.logException;

public class RenderPlantuml {

	public static boolean suppressRenderExceptions = true;

	public static void renderPlantumlTest(FileFormat fileFormat, String outputDir, String testName, String... config) throws IOException {

		final SFile outputFile = calculateOutputFile(outputDir, fileFormat, testName);

		log("--- Rendering %s ---", outputFile.getPath());

		try {
			outputFile.getParentFile().mkdirs();

			final String source = loadResourceAsString("/simple-diagrams/" + testName + ".puml");

			final SourceStringReader ssr = new SourceStringReader(Defines.createEmpty(), source, Arrays.asList(config));

			final Diagram diagram = ssr.getBlocks().get(0).getDiagram();

			try (OutputStream os = outputFile.createBufferedOutputStream()) {
				diagram.exportDiagram(os, 0, new FileFormatOption(fileFormat));
			}

			if (diagram instanceof PSystemError) {
				final PSystemError pSystemError = (PSystemError) diagram;
				final ErrorUml error = pSystemError.getFirstError();
				final String ignore = String.format("' Expect error \"%s\" on line %d", error.getError(), error.getLineLocation().getPosition());
				if (source.contains(ignore)) return;
				log("(The following error can be ignored by putting [%s] in the source)", ignore);
				pSystemError.getPureAsciiFormatted().forEach(Logging::log);
				if (!suppressRenderExceptions) throw new RuntimeException("Diagram has error");
			}

		} catch (Exception e) {
			if (suppressRenderExceptions) {
				logException(e, "Error rendering '%s'", outputFile.getPath());
				outputFile.delete();
			} else {
				throw e;
			}
		}
	}
}
