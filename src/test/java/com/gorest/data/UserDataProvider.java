package com.gorest.data;

import com.gorest.models.users;
import com.gorest.utils.CsvReaderUtil;
import com.gorest.utils.RandomDataGenerator;
import org.testng.annotations.DataProvider;

import java.util.List;

public class UserDataProvider {
	@DataProvider(name = "createUserData")

	public Object[][] provideCreateUserData() {
		System.out.println("📁 Looking for: " + "usersPositives.csv");
		System.out.println("📁 Resolved URL: " + CsvReaderUtil.class.getClassLoader().getResource("usersPositives.csv"));

		List<users> allUsers = CsvReaderUtil.readCsv("usersPositives.csv", users.class);

		System.out.println("🔍 Total users read from CSV: " + allUsers.size());

		System.out.println("🧵 DEBUG: Printing all parsed rows from CSV...");
		for (users u : allUsers) {
		    System.out.println("➡️ Parsed: " + u);
		}
	    // ✅ Filter only POST type rows
	    List<users> postUsers = allUsers.stream()
	        .filter(u -> u.getTestType() != null && "POST".equalsIgnoreCase(u.getTestType().trim()))
	        .toList();

	    System.out.println("✅ POST users filtered from CSV: " + postUsers.size());

	    Object[][] testData = new Object[postUsers.size()][4];
	    for (int i = 0; i < postUsers.size(); i++) {
	        users u = postUsers.get(i);

	        String name = (u.getName() == null || u.getName().isEmpty())
	            ? RandomDataGenerator.generateFullName() : u.getName();
	        String email = (u.getEmail() == null || u.getEmail().isEmpty())
	            ? RandomDataGenerator.generateEmail() : u.getEmail();

	        testData[i][0] = name;
	        testData[i][1] = email;
	        testData[i][2] = u.getGender();
	        testData[i][3] = u.getStatus();
	    }

	    return testData;
	}


    // ✅ PUT data from users.csv
    @DataProvider(name = "updateUserData")
    public Object[][] provideUpdateUserData() {
        List<users> allUsers = CsvReaderUtil.readCsv("usersPositives.csv", users.class);

        List<users> putUsers = allUsers.stream()
        		.filter(u -> u.getTestType() != null && "PUT".equalsIgnoreCase(u.getTestType().trim()))
        		.toList();

        Object[][] testData = new Object[putUsers.size()][4];
        for (int i = 0; i < putUsers.size(); i++) {
            users u = putUsers.get(i);
            testData[i][0] = u.getName().trim();
            testData[i][1] = u.getEmail().trim();
            testData[i][2] = u.getGender().trim();
            testData[i][3] = u.getStatus().trim();
        }

        return testData;
    }

    // ✅ PATCH data from users.csv
    @DataProvider(name = "patchUserData")
    public Object[][] providePatchUserData() {
        List<users> allUsers = CsvReaderUtil.readCsv("usersPositives.csv", users.class);

        List<users> putUsers = allUsers.stream()
        		.filter(u -> u.getTestType() != null && "PATCH".equalsIgnoreCase(u.getTestType().trim()))
        		.toList();

        Object[][] testData = new Object[putUsers.size()][4];
        for (int i = 0; i < putUsers.size(); i++) {
            users u = putUsers.get(i);
            testData[i][0] = u.getName().trim();
            testData[i][1] = u.getEmail().trim();
            testData[i][2] = u.getGender().trim();
            testData[i][3] = u.getStatus().trim();
        }

        return testData;
    }

    // ✅ Hardcoded negative test data
    @DataProvider(name = "invalidUsers")
    public Object[][] provideInvalidUsers() {
        return new Object[][]{
                {"", "", "", ""},
                {"John Doe", "invalidEmail", "other", "on"}
        };
    }
}
