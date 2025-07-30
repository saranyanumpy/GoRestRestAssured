package com.gorest.data;

import com.gorest.models.users;
import com.gorest.utils.CsvReaderUtil;
import com.gorest.utils.RandomDataGenerator;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.stream.Collectors;

public class UserDataProvider {
	@DataProvider(name = "createUserData")

	public Object[][] provideCreateUserData() {
//		System.out.println("📁 Looking for: " + "usersPositives.csv");
//		System.out.println("📁 Resolved URL: " + CsvReaderUtil.class.getClassLoader().getResource("usersPositives.csv"));

		List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

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
        List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

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
        List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

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

    @DataProvider(name = "existingUserData")
    public Object[][] existingJSONCreateUserData() {
//    	System.out.println("📁 Looking for: " + "usersPositives.csv");
//		System.out.println("📁 Resolved URL: " + CsvReaderUtil.class.getClassLoader().getResource("usersPositives.csv"));

		List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

		System.out.println("🔍 Total users read from CSV: " + allUsers.size());

		System.out.println("🧵 DEBUG: Printing all parsed rows from CSV...");
		for (users u : allUsers) {
		    System.out.println("➡️ Parsed: " + u);
		}
		
//	    // ✅ Filter only POST type rows
	    List<users> existUsers = allUsers.stream()
	        .filter(u -> u.getTestType() != null && "POSTEXISTING".equalsIgnoreCase(u.getTestType().trim()))
	        .toList();
     // Debug output for filtered users
        System.out.println("✅ POST-EXISTING users filtered from CSV: " + existUsers.size());

        // Prepare the test data array
        Object[][] testData = new Object[existUsers.size()][4];

        // Loop through the filtered users and prepare data
        for (int i = 0; i < existUsers.size(); i++) {
            users u = existUsers.get(i);
     // Populate the test data array
            testData[i][0] = u.getName(); // User name
            testData[i][1] = u.getEmail(); // User email
            testData[i][2] = u.getGender(); // User gender
            testData[i][3] = u.getStatus(); // User status
        }

        // Return the test data
        return testData;
    }
   
    @DataProvider(name = "missingUserName")
    public Object[][] missingJSONCreateUserData() {
//        System.out.println("📁 Looking for: " + "usersPositives.csv");
//        System.out.println("📁 Resolved URL: " + CsvReaderUtil.class.getClassLoader().getResource("usersPositives.csv"));

        List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

        System.out.println("🔍 Total users read from CSV: " + allUsers.size());

        System.out.println("🧵 DEBUG: Printing all parsed rows from CSV...");
        for (users u : allUsers) {
            System.out.println("➡️ Parsed: " + u);
        }

        // Filter only rows where the testType is "POST-MISSINGNAME"
        List<users> missingNameUsers = allUsers.stream()
            .filter(u -> u.getTestType() != null && "POST-MISSINGNAME".equalsIgnoreCase(u.getTestType().trim()))
            .toList();

        System.out.println("✅ POST-MISSINGNAME users filtered from CSV: " + missingNameUsers.size());

        // Prepare the test data array
        Object[][] testData = new Object[missingNameUsers.size()][4];

        // Loop through the filtered users and prepare data
        for (int i = 0; i < missingNameUsers.size(); i++) {
            users u = missingNameUsers.get(i);
            testData[i][0] = null; // Set name as null for the missing name scenario
            testData[i][1] = u.getEmail(); // User email
            testData[i][2] = u.getGender(); // User gender
            testData[i][3] = u.getStatus(); // User status
        }

        return testData;
    }

    @DataProvider(name = "invalidUserEmail")
    public Object[][] invalidEmailCreateUserData() {
//        System.out.println("📁 Looking for: " + "usersPositives.csv");
//        System.out.println("📁 Resolved URL: " + CsvReaderUtil.class.getClassLoader().getResource("usersPositives.csv"));

        List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

        System.out.println("🔍 Total users read from CSV: " + allUsers.size());

        System.out.println("🧵 DEBUG: Printing all parsed rows from CSV...");
        for (users u : allUsers) {
            System.out.println("➡️ Parsed: " + u);
        }

        // Filter only rows where the testType is "POST-MISSINGNAME"
        List<users> missingNameUsers = allUsers.stream()
            .filter(u -> u.getTestType() != null && "POST-INVALIDEMAIL".equalsIgnoreCase(u.getTestType().trim()))
            .toList();

        System.out.println("✅ POST-MISSINGNAME users filtered from CSV: " + missingNameUsers.size());

        // Prepare the test data array
        Object[][] testData = new Object[missingNameUsers.size()][4];

        // Loop through the filtered users and prepare data
        for (int i = 0; i < missingNameUsers.size(); i++) {
            users u = missingNameUsers.get(i);
            testData[i][0] = u.getName(); // Set name as null for the missing name scenario
            testData[i][1] = u.getEmail(); // User email
            testData[i][2] = u.getGender(); // User gender
            testData[i][3] = u.getStatus(); // User status
        }

        return testData;
    }

    
    @DataProvider(name = "invalidUserGender")
    public Object[][] invalidGenderCreateUserData() {
      //  System.out.println("📁 Looking for: " + "usersPositives.csv");
       // System.out.println("📁 Resolved URL: " + CsvReaderUtil.class.getClassLoader().getResource("users.csv"));

        List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

        System.out.println("🔍 Total users read from CSV: " + allUsers.size());

        System.out.println("🧵 DEBUG: Printing all parsed rows from CSV...");
        for (users u : allUsers) {
            System.out.println("➡️ Parsed: " + u);
        }

        // Filter only rows where the testType is "POST-MISSINGNAME"
        List<users> missingNameUsers = allUsers.stream()
            .filter(u -> u.getTestType() != null && "POST-INVALIDGENDER".equalsIgnoreCase(u.getTestType().trim()))
            .toList();

        System.out.println("✅ POST-MISSINGNAME users filtered from CSV: " + missingNameUsers.size());

        // Prepare the test data array
        Object[][] testData = new Object[missingNameUsers.size()][4];

        // Loop through the filtered users and prepare data
        for (int i = 0; i < missingNameUsers.size(); i++) {
            users u = missingNameUsers.get(i);
            testData[i][0] = u.getName(); // Set name as null for the missing name scenario
            testData[i][1] = u.getEmail(); // User email
            testData[i][2] = u.getGender(); // User gender
            testData[i][3] = u.getStatus(); // User status
        }

        return testData;
    }
    @DataProvider(name = "invalidUserId")
    public Object[][] invalidUserIdData() {
//        System.out.println("📁 Looking for: " + "usersNegatives.csv");
//        System.out.println("📁 Resolved URL: " + CsvReaderUtil.class.getClassLoader().getResource("users.csv"));

        List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

        System.out.println("🔍 Total users read from CSV: " + allUsers.size());

        System.out.println("🧵 DEBUG: Printing all parsed rows from CSV...");
        for (users u : allUsers) {
            System.out.println("➡️ Parsed: " + u);
        }

        // Filter only rows where the testType is "GET-INVALIDID"
        List<users> invalidIdUsers = allUsers.stream()
            .filter(u -> u.getTestType() != null && "GET-INVALIDID".equalsIgnoreCase(u.getTestType().trim()))
            .toList();

        System.out.println("✅ GET-INVALIDID users filtered from CSV: " + invalidIdUsers.size());

        // Prepare the test data array
        Object[][] testData = new Object[invalidIdUsers.size()][1];

        // Loop through the filtered users and prepare data
        for (int i = 0; i < invalidIdUsers.size(); i++) {
            users u = invalidIdUsers.get(i);
            testData[i][0] = u.getId(); // assuming getId() returns the user ID (String or int)
        }

        return testData;
    }

    @DataProvider(name = "updateInvalidEmail")
    public Object[][] updateInvalidEmail() {

        List<users> allUsers = CsvReaderUtil.readCsv("users.csv", users.class);

        List<users> invalidEmailUsers = allUsers.stream()
            .filter(u -> u.getTestType() != null && "PUT-BY-INVALIDEMAIL".equalsIgnoreCase(u.getTestType().trim()))
            .toList();

        Object[][] testData = new Object[invalidEmailUsers.size()][5];

        for (int i = 0; i < invalidEmailUsers.size(); i++) {
            users u = invalidEmailUsers.get(i);
            testData[i][0] = String.valueOf(u.getId()); // Make sure it's String
            testData[i][1] = u.getName();
            testData[i][2] = u.getEmail();
            testData[i][3] = u.getGender();
            testData[i][4] = u.getStatus();
        }

        return testData;
    }


}
