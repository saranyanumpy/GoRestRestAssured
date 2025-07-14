package com.gorest.data;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.gorest.models.posts;
import com.gorest.utils.CsvReaderUtil;

public class PostsDataProvider {

	@DataProvider(name = "createPostsData")
	public Object[][] provideCreatePostsData() {
	    List<posts> postList = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

	    List<posts> postRows = postList.stream()
	            .filter(p -> "POST".equalsIgnoreCase(p.getTestType()))
	            .toList();

	    Object[][] testData = new Object[postRows.size()][3];
	    for (int i = 0; i < postRows.size(); i++) {
	        posts p = postRows.get(i);
	        testData[i][0] = p.getLabel();
	        testData[i][1] = p.getTitle();
	        testData[i][2] = p.getBody();
	    }

	    return testData;
	}


//
//    // ✅ PUT data from users.csv
//    @DataProvider(name = "updateUserData")
//    public Object[][] provideUpdateUserData() {
//        List<users> allUsers = CsvReaderUtil.readCsv("usersPositives.csv", users.class);
//
//        List<users> putUsers = allUsers.stream()
//        		.filter(u -> u.getTestType() != null && "PUT".equalsIgnoreCase(u.getTestType().trim()))
//        		.toList();
//
//        Object[][] testData = new Object[putUsers.size()][4];
//        for (int i = 0; i < putUsers.size(); i++) {
//            users u = putUsers.get(i);
//            testData[i][0] = u.getName().trim();
//            testData[i][1] = u.getEmail().trim();
//            testData[i][2] = u.getGender().trim();
//            testData[i][3] = u.getStatus().trim();
//        }
//
//        return testData;
//    }
//
//    // ✅ PATCH data from users.csv
//    @DataProvider(name = "patchUserData")
//    public Object[][] providePatchUserData() {
//        List<users> allUsers = CsvReaderUtil.readCsv("usersPositives.csv", users.class);
//
//        List<users> putUsers = allUsers.stream()
//        		.filter(u -> u.getTestType() != null && "PATCH".equalsIgnoreCase(u.getTestType().trim()))
//        		.toList();
//
//        Object[][] testData = new Object[putUsers.size()][4];
//        for (int i = 0; i < putUsers.size(); i++) {
//            users u = putUsers.get(i);
//            testData[i][0] = u.getName().trim();
//            testData[i][1] = u.getEmail().trim();
//            testData[i][2] = u.getGender().trim();
//            testData[i][3] = u.getStatus().trim();
//        }
//
//        return testData;
//    }

    // ✅ Hardcoded negative test data
    @DataProvider(name = "invalidUsers")
    public Object[][] provideInvalidUsers() {
        return new Object[][]{
                {"", "", "", ""},
                {"John Doe", "invalidEmail", "other", "on"}
        };
    }
}

