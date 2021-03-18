package net.sourceforge.plantuml.test.playground;

import net.sourceforge.plantuml.FileFormat;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableSet;
import static net.sourceforge.plantuml.FileFormat.PNG;
import static net.sourceforge.plantuml.test.playground.Data.calculateOutputFile;
import static net.sourceforge.plantuml.test.playground.RenderPlantuml.renderPlantumlTest;

/**
 * An immutable set of "test names" plus some helper methods.
 */
class Group {

	public static Group testGroup(String... names) {
		return testGroup(Arrays.asList(names));
	}

	public static Group testGroup(Collection<String> names) {
		return new Group(names);
	}

	private final Set<String> names;

	private Group(Collection<String> names) {
		this.names = unmodifiableSet(new TreeSet<>(names));
	}

	public Group add(String... names) {
		return add(Arrays.asList(names));
	}

	public Group add(Group... others) {
		final Set<String> result = new HashSet<>(this.names);
		for (Group other : others) {
			result.addAll(other.names);
		}
		return testGroup(result);
	}

	public Group add(Collection<String> names) {
		final Set<String> result = new HashSet<>(this.names);
		result.addAll(names);
		return testGroup(result);
	}

	public Group subtract(String... names) {
		return subtract(Arrays.asList(names));
	}

	public Group subtract(Group... others) {
		final Set<String> result = new HashSet<>(this.names);
		for (Group other : others) {
			result.removeAll(other.names);
		}
		return testGroup(result);
	}

	public Group subtract(Collection<String> names) {
		final Set<String> result = new HashSet<>(this.names);
		result.removeAll(names);
		return testGroup(result);
	}

	public Group difference(Group other) {
		final Set<String> result = new HashSet<>();
		for (String name : this.names) {
			if (!other.names.contains(name)) {
				result.add(name);
			}
		}
		for (String name : other.names) {
			if (!this.names.contains(name)) {
				result.add(name);
			}
		}
		return testGroup(result);
	}

	public Grid gridHorizontal(String... dirs) {
		final String[] names = this.names.toArray(new String[]{});
		final File[][] files = new File[dirs.length][names.length];
		for (int row = 0; row < dirs.length; row++) {
			for (int col = 0; col < names.length; col++) {
				files[row][col] = calculateOutputFile(dirs[row], PNG, names[col]).conv();
			}
		}
		return new Grid(dirs, names, files);
	}

	public Grid gridVertical(String... dirs) {
		final String[] names = this.names.toArray(new String[]{});
		final File[][] files = new File[names.length][dirs.length];
		for (int row = 0; row < names.length; row++) {
			for (int col = 0; col < dirs.length; col++) {
				files[row][col] = calculateOutputFile(dirs[col], PNG, names[row]).conv();
			}
		}
		return new Grid(names, dirs, files);
	}

	public Group markdownHorizontal(String filename, String... dirs) throws IOException {
		gridHorizontal(dirs).markdown(filename);
		return this;
	}

	public Group markdownVertical(String filename, String... dirs) throws IOException {
		gridVertical(dirs).markdown(filename);
		return this;
	}

	public Group pngHorizontal(String filename, String... dirs) throws IOException {
		gridHorizontal(dirs).png(filename);
		return this;
	}

	public Group pngVertical(String filename, String... dirs) throws IOException {
		gridVertical(dirs).png(filename);
		return this;
	}

	public Group render(String... config) throws IOException {
		for (String name : names) {
			renderPlantumlTest(PNG, "rendered", name, config);
		}
		return this;
	}

	public Group render(FileFormat fileFormat, String outputDir, String... config) throws IOException {
		for (String name : names) {
			renderPlantumlTest(fileFormat, outputDir, name, config);
		}
		return this;
	}

	public Stream<String> stream() {
		return names.stream();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Group && names.equals(((Group) obj).names);
	}

	public String[] toArray() {
		return names.toArray(new String[]{});
	}

	@Override
	public String toString() {
		return names.toString();
	}
}
