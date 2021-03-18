package net.sourceforge.plantuml.test.playground;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.security.SFile;

import java.util.ArrayList;
import java.util.List;

import static net.sourceforge.plantuml.test.playground.Group.testGroup;

public class Data {

	public static final SFile PLAYGROUND_OUTPUT_DIR = new SFile("target/test-playground");

	public static final Group ALL;

	/**
	 * Some of these might not do anything with skinparam but they at least allow it
	 */
	public static final Group ALL_ALLOWING_SKINPARAM;

	/**
	 * Things that users would probably consider a "diagram"
	 */
	public static final Group ALL_DIAGRAMS;

	public static final Group ALL_ERRORS;

	public static final Group ALL_EGGS;

	public static final Group ALL_HELP;

	public static final Group ALL_INFO;

	public static final Group ALL_MISC;

	/**
	 * Subclasses of TitledDiagram
	 */
	public static final Group ALL_TITLED_DIAGRAMS;

	static {
		final String[] data = new String[]{
			"activity DPT",           // net.sourceforge.plantuml.activitydiagram.ActivityDiagram
			"activity3 DPT",          // net.sourceforge.plantuml.activitydiagram3.ActivityDiagram3
			"apple_two G",            // net.sourceforge.plantuml.eggs.PSystemAppleTwo
			"board DPT",              // net.sourceforge.plantuml.board.BoardDiagram
			"bpm DT",                 // net.sourceforge.plantuml.bpm.BpmDiagram
			"charlie G",              // net.sourceforge.plantuml.eggs.PSystemCharlie
			"class DPT",              // net.sourceforge.plantuml.classdiagram.ClassDiagram
			"colors I",               // net.sourceforge.plantuml.eggs.PSystemColors
			"creole M",               // net.sourceforge.plantuml.creole.legacy.PSystemCreole
			"dedication M",           // net.sourceforge.plantuml.dedication.PSystemDedication
			"definition M",           // net.sourceforge.plantuml.definition.PSystemDefinition
			"description DPT",        // net.sourceforge.plantuml.descdiagram.DescriptionDiagram
			"ditaa DM",               // net.sourceforge.plantuml.ditaa.PSystemDitaa
			"donors I",               // net.sourceforge.plantuml.donors.PSystemDonors
			"dot D",                  // net.sourceforge.plantuml.directdot.PSystemDot
			"error_preprocessor E",   // net.sourceforge.plantuml.error.PSystemErrorPreprocessor
			"error_v2 E",             // net.sourceforge.plantuml.error.PSystemErrorV2
			"flow DT",                // net.sourceforge.plantuml.flowdiagram.FlowDiagram
			"gantt DPT",              // net.sourceforge.plantuml.project.GanttDiagram
			"git DPT",                // net.sourceforge.plantuml.gitlog.GitDiagram
			"help HT",                // net.sourceforge.plantuml.help.Help
			"help_colors HT",         // net.sourceforge.plantuml.help.Help
			"help_fonts HT",          // net.sourceforge.plantuml.help.Help
			"help_keywords HT",       // net.sourceforge.plantuml.help.Help
			"help_skinparams HT",     // net.sourceforge.plantuml.help.Help
			"help_types HT",          // net.sourceforge.plantuml.help.Help
			"jcckit DP",              // net.sourceforge.plantuml.jcckit.PSystemJcckit
			"json DT",                // net.sourceforge.plantuml.jsondiagram.JsonDiagram
			"keycheck I",             // net.sourceforge.plantuml.version.PSystemKeycheck
			"keygen I",               // net.sourceforge.plantuml.version.PSystemKeygen
			"latex DP",               // net.sourceforge.plantuml.math.PSystemLatex
			"license I",              // net.sourceforge.plantuml.version.PSystemLicense
			"listfonts I",            // net.sourceforge.plantuml.font.PSystemListFonts
			"listopeniconic I",       // net.sourceforge.plantuml.openiconic.PSystemListOpenIconic
			// TODO confused about net.sourceforge.plantuml.sprite.ListSpriteDiagram vs net.sourceforge.plantuml.sprite.PSystemListInternalSprites
			//      so not well categorised.  Seems like both use the "listsprite" command but only one can ever run?
			"listsprites I",
			"math DP",                // net.sourceforge.plantuml.math.PSystemMath
			"mindmap DPT",            // net.sourceforge.plantuml.mindmap.MindMapDiagram
			"network DPT",            // net.sourceforge.plantuml.nwdiag.NwDiagram
			"openiconic I",           // net.sourceforge.plantuml.openiconic.PSystemOpenIconic
			"path DM",                // net.sourceforge.plantuml.eggs.PSystemPath
			"rip G",                  // net.sourceforge.plantuml.eggs.PSystemRIP
			"salt DPT",               // net.sourceforge.plantuml.salt.PSystemSalt
			"sequence DPT",           // net.sourceforge.plantuml.sequencediagram.SequenceDiagram
			"skinparameters I",       // net.sourceforge.plantuml.donors.PSystemSkinparameterList
			"state DPT",              // net.sourceforge.plantuml.statediagram.StateDiagram
			"stdlib IT",              // net.sourceforge.plantuml.sprite.StdlibDiagram
			"sudoku M",               // net.sourceforge.plantuml.sudoku.PSystemSudoku
			"timing DPT",             // net.sourceforge.plantuml.timingdiagram.TimingDiagram
			"version I",              // net.sourceforge.plantuml.version.PSystemVersion
			"wbs DPT",                // net.sourceforge.plantuml.wbs.WBSDiagram
			"welcome I",              // net.sourceforge.plantuml.eggs.PSystemWelcome
			"wire DPT",               // net.sourceforge.plantuml.wire.WireDiagram
			"xearth M",               // net.sourceforge.plantuml.acearth.PSystemXearth
			"yaml DT",                // net.sourceforge.plantuml.jsondiagram.JsonDiagram (via net.sourceforge.plantuml.yaml.YamlDiagramFactory)
		};

		final List<String> all = new ArrayList<>();
		final List<String> allAllowingSkinparam = new ArrayList<>();
		final List<String> allDiagrams = new ArrayList<>();
		final List<String> allEggs = new ArrayList<>();
		final List<String> allErrors = new ArrayList<>();
		final List<String> allHelp = new ArrayList<>();
		final List<String> allInfo = new ArrayList<>();
		final List<String> allMisc = new ArrayList<>();
		final List<String> allTitledDiagrams = new ArrayList<>();

		for (String d : data) {
			String[] parts = d.split(" ");
			String name = parts[0];
			String groups = parts.length > 1 ? parts[1] : "";
			all.add(name);
			if (groups.contains("D")) allDiagrams.add(name);
			if (groups.contains("E")) allErrors.add(name);
			if (groups.contains("G")) allEggs.add(name);
			if (groups.contains("H")) allHelp.add(name);
			if (groups.contains("I")) allInfo.add(name);
			if (groups.contains("M")) allMisc.add(name);
			if (groups.contains("P")) allAllowingSkinparam.add(name);
			if (groups.contains("T")) allTitledDiagrams.add(name);
		}

		ALL = testGroup(all);
		ALL_ALLOWING_SKINPARAM = testGroup(allAllowingSkinparam);
		ALL_DIAGRAMS = testGroup(allDiagrams);
		ALL_EGGS = testGroup(allEggs);
		ALL_ERRORS = testGroup(allErrors);
		ALL_HELP = testGroup(allHelp);
		ALL_INFO = testGroup(allInfo);
		ALL_MISC = testGroup(allMisc);
		ALL_TITLED_DIAGRAMS = testGroup(allTitledDiagrams);
	}

	public static SFile calculateOutputFile(String outputDir, FileFormat fileFormat, String testName) {
		return PLAYGROUND_OUTPUT_DIR
			.file(outputDir)
			.file(fileFormat.changeName(testName, 0));
	}
}
