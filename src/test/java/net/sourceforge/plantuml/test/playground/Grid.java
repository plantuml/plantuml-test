package net.sourceforge.plantuml.test.playground;

import java.io.File;
import java.io.IOException;

import static net.sourceforge.plantuml.test.playground.GridMarkdown.writeGridAsMarkdown;
import static net.sourceforge.plantuml.test.playground.GridPng.writeGridAsPng;

public class Grid {
	public final String[] rowNames;
	public final String[] colNames;
	public final File[][] files;

	public Grid(String[] rowNames, String[] colNames, File[][] paths) {
		this.rowNames = rowNames;
		this.colNames = colNames;
		this.files = paths;
	}

	public Grid markdown(String filename) throws IOException {
		writeGridAsMarkdown(this, filename);
		return this;
	}

	public Grid png(String filename) throws IOException {
		writeGridAsPng(this, filename);
		return this;
	}
}
