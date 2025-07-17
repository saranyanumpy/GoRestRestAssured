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
	@DataProvider(name = "UpdatePostTest")
	public Object[][] provideUpdatePostsData() {
	    List<posts> postList = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

	    List<posts> postRows = postList.stream()
	            .filter(p -> "PUT".equalsIgnoreCase(p.getTestType()))
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

	
//    // ✅ PATCH data from users.csv
    @DataProvider(name = "PatchPostTest")
	public Object[][] providePatchPostsData() {
	    List<posts> postList = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

	    List<posts> postRows = postList.stream()
	            .filter(p -> "PATCH".equalsIgnoreCase(p.getTestType()))
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

    // ✅ Hardcoded negative test data
    @DataProvider(name = "invalidUsers")
    public Object[][] provideInvalidUsers() {
        return new Object[][]{
                {"", "", "", ""},
                {"John Doe", "invalidEmail", "other", "on"}
        };
    }
}

