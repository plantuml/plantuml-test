package net.sourceforge.plantuml.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CachingResourceLoader {

	private static final Map<String, String> CACHE = new HashMap<>();

	public static String loadResourceAsString(String path) throws IOException {
		if (CACHE.containsKey(path)) {
			return CACHE.get(path);
		}

		try (InputStream in = CachingResourceLoader.class.getResourceAsStream(path)) {
			if (in == null) {
				throw new IOException("No resource found for " + path);
			}
			// Kludge to read InputStream as String
			// from https://stackoverflow.com/questions/6068197/utils-to-read-resource-text-file-to-string-java
			final String content = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
			CACHE.put(path, content);
			return content;
		}
	}
}

