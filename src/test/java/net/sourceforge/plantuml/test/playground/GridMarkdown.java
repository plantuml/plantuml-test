package net.sourceforge.plantuml.test.playground;

import net.sourceforge.plantuml.security.SFile;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import static net.sourceforge.plantuml.test.playground.Logging.log;
import static net.sourceforge.plantuml.test.playground.Data.PLAYGROUND_OUTPUT_DIR;

public class GridMarkdown {

	public static void writeGridAsMarkdown(Grid grid, String filename) throws IOException {
		final SFile outputFile = PLAYGROUND_OUTPUT_DIR.file(filename + ".md");
		log("--- Rendering %s ---", outputFile.getPath());
		outputFile.getParentFile().mkdirs();

		final StringBuilder b = new StringBuilder();

		// header
		b.append("| ");
		for (String col : grid.colNames) {
			b.append("| `");
			b.append(escapeBacktick(col));
			b.append("` ");
		}
		b.append("|\n");

		// line
		b.append("|---");
		for (String ignored : grid.colNames) {
			b.append("|---");
		}
		b.append("|\n");

		// data

		final int prefixLength = (PLAYGROUND_OUTPUT_DIR.getPath() + File.separator).length();

		for (int row = 0; row < grid.rowNames.length; row++) {
			b.append("| `");
			b.append(escapeBacktick(grid.rowNames[row]));
			b.append("` ");
			for (int col = 0; col < grid.colNames.length; col++) {
				b.append("| ![](");
				b.append(grid.files[row][col].getPath().substring(prefixLength));
				b.append(") ");
			}
			b.append("|\n");
		}

		try (Writer writer = outputFile.createPrintWriter()) {
			writer.write(b.toString());
		}
	}

	private static String escapeBacktick(String string) {
		return string.replace('`', '_');
	}
}
