package com.gorest.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.nio.file.Files;

public class FileUtil {
	public static String readFileFromResources(String filePath) {
	    try {
	        Path path = Paths.get(Objects.requireNonNull(
	            FileUtil.class.getClassLoader().getResource(filePath)).toURI());

	        return Files.readString(path);
	    } catch (Exception e) {
	        throw new RuntimeException("Error reading test file: " + filePath, e);
	    }
	}

} 