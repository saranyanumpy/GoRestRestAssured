package com.gorest.utils;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CsvReaderUtil {

	  public static <T> List<T> readCsv(String filePath, Class<T> clazz) {
	        // ✅ Prepend "data/" to the file path
	        String fullPath = "data/" + filePath;

	        try (InputStream inputStream = CsvReaderUtil.class.getClassLoader().getResourceAsStream(fullPath)) {
	            if (inputStream == null) {
	                throw new RuntimeException("File not found in resources/data/: " + fullPath);
	            }

	            Reader reader = new InputStreamReader(inputStream);

	            return new CsvToBeanBuilder<T>(reader)
	                    .withType(clazz)
	                    .withIgnoreLeadingWhiteSpace(true)
	                    .withSkipLines(0)
	                    .build()
	                    .parse();

	        } catch (Exception e) {
	            throw new RuntimeException("Failed to read CSV: " + fullPath, e);
	        }
	    }
	}

//public static <T> List<T> readCsv(String filePath, Class<T> clazz) {
//    try (InputStream inputStream = CsvReaderUtil.class.getClassLoader().getResourceAsStream(filePath)) {
//        if (inputStream == null) {
//            throw new RuntimeException("File not found in resources/data/: " + filePath);
//        }
//
//        Reader reader = new InputStreamReader(inputStream);
//
//        // 🔍 Debug: Print raw CSV content
//        System.out.println("🔍 Raw CSV Content:");
//        new BufferedReader(reader).lines().forEach(System.out::println);
//
//        // Reset the reader again for actual parsing
//        InputStream inputStream2 = CsvReaderUtil.class.getClassLoader().getResourceAsStream(filePath);
//        Reader parsingReader = new InputStreamReader(inputStream2);
//
//        return new CsvToBeanBuilder<T>(parsingReader)
//                .withType(clazz)
//                .withIgnoreLeadingWhiteSpace(true)
//                .withSkipLines(0) // set to 0 if first line is header
//                .build()
//                .parse();
//
//    } catch (Exception e) {
//        throw new RuntimeException("Failed to read CSV: " + filePath, e);
//    }
//}
