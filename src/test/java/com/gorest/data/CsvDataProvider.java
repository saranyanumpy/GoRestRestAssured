package com.gorest.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvDataProvider {

    // ✅ CSV reading logic
    private static Object[][] readCsv(String fileName) {
        try {
            InputStream inputStream = CsvDataProvider.class.getClassLoader().getResourceAsStream("data/" + fileName);
            InputStreamReader reader = new InputStreamReader(inputStream);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()                // Uses first row as header
                    .setSkipHeaderRecord(true) // Skips header in returned data
                    .build()
                    .parse(reader);

            List<Object[]> data = new ArrayList<>();
            for (CSVRecord record : records) {
                List<String> row = new ArrayList<>();
                record.forEach(row::add);
                data.add(row.toArray());
            }

            return data.toArray(new Object[0][]);
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file: " + fileName, e);
        }
    }

    // ✅ TestNG Data Providers using the readCsv() method
    @DataProvider(name = "userDataFromCsv")
    public static Object[][] provideUserDataFromCsv() {
        return readCsv("users.csv");
    }

    @DataProvider(name = "postDataFromCsv")
    public static Object[][] providePostDataFromCsv() {
        return readCsv("posts.csv");
    }

    @DataProvider(name = "commentDataFromCsv")
    public static Object[][] provideCommentDataFromCsv() {
        return readCsv("comments.csv");
    }
}
