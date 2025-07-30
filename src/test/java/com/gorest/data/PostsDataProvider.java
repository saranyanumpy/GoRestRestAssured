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
    
    @DataProvider(name = "createPostInvalidUserId")
	public Object[][] provideCreateInvalidUserIdPostsData() {
	    List<posts> postList = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

	    List<posts> postRows = postList.stream()
	            .filter(p -> "POST-INVALID-USERID".equalsIgnoreCase(p.getTestType()))
	            .toList();

	    Object[][] testData = new Object[postRows.size()][3];
	    for (int i = 0; i < postRows.size(); i++) {
	        posts p = postRows.get(i);
	        
	        testData[i][0] = p.getTitle();
	        testData[i][1] = p.getBody();
	        testData[i][2] = p.getUserId();
	    }

	    return testData;
	}


    @DataProvider(name = "htmlInjectionPostData")
    public Object[][] provideHtmlInjectionPostData() {
        List<posts> allPosts = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

        List<posts> filtered = allPosts.stream()
                .filter(p -> p.getTestType() != null && "POST-HTML-INJECTION".equalsIgnoreCase(p.getTestType().trim()))
                .toList();

        Object[][] testData = new Object[filtered.size()][3];
        for (int i = 0; i < filtered.size(); i++) {
            posts p = filtered.get(i);
            testData[i][0] = p.getTitle();
            testData[i][1] = p.getBody();
            testData[i][2] = p.getUserId();
        }

        return testData;
    }
    @DataProvider(name = "missingUserField")
    public Object[][] provideMissingFieldPostData() {
        List<posts> allPosts = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

        List<posts> filtered = allPosts.stream()
                .filter(p -> p.getTestType() != null && "POST-MISSING-FIELD".equalsIgnoreCase(p.getTestType().trim()))
                .toList();

        Object[][] testData = new Object[filtered.size()][3];
        for (int i = 0; i < filtered.size(); i++) {
            posts p = filtered.get(i);
            testData[i][0] = p.getTitle();
            testData[i][1] = p.getBody();
            testData[i][2] = p.getUserId();
        }

        return testData;
    }
    
    @DataProvider(name = "invalidPostId")
    public Object[][] provideInvalidPostIds() {
        List<posts> postList = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

        List<posts> invalidPosts = postList.stream()
            .filter(p -> p.getTestType() != null && "GET-BY-INVALID-ID".equalsIgnoreCase(p.getTestType().trim()))
            .toList();

        Object[][] testData = new Object[invalidPosts.size()][1];
        for (int i = 0; i < invalidPosts.size(); i++) {
            posts p = invalidPosts.get(i);
            testData[i][0] = p.getPostId();  // assuming this field exists
        }

        return testData;
    }
    @DataProvider(name = "invalidPatchPostData")
    public Object[][] provideInvalidPatchPostData() {
        List<posts> allPosts = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

        List<posts> filtered = allPosts.stream()
                .filter(p -> p.getTestType() != null && "PATCH-INVALID-POSTID".equalsIgnoreCase(p.getTestType().trim()))
                .toList();

        Object[][] testData = new Object[filtered.size()][4];
        for (int i = 0; i < filtered.size(); i++) {
            posts p = filtered.get(i);
            testData[i][0] = p.getLabel(); // This should be your invalid postId (e.g., "abcd")
            testData[i][1] = p.getTitle();
            testData[i][2] = p.getBody();
            testData[i][3] = p.getUserId();
        }

        return testData;
    }
    @DataProvider(name = "missingUpdateUserField")
    public Object[][] provideUpdateMissingFieldPostData() {
        List<posts> allPosts = CsvReaderUtil.readCsv("postsPositive.csv", posts.class);

        // Filter out posts with missing fields for update
        List<posts> filtered = allPosts.stream()
                .filter(p -> p.getTestType() != null && "UPDATE-POST-MISSING-FIELD".equalsIgnoreCase(p.getTestType().trim()))
                .toList();

        // Update test data to provide the missing postId as the first parameter
        Object[][] testData = new Object[filtered.size()][4];
        for (int i = 0; i < filtered.size(); i++) {
            posts p = filtered.get(i);
            testData[i][0] = p.getPostId();  // Adding postId as the first parameter
            testData[i][1] = p.getTitle();
            testData[i][2] = p.getBody();
            testData[i][3] = p.getUserId();
        }

        return testData;
    }


}

