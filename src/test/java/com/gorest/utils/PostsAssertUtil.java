package com.gorest.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;

public class PostsAssertUtil {

    /**
     * Assert that a user was created successfully.
     * Validates status code and all expected fields match.
     */
	 private static final Logger logger = LoggerFactory.getLogger(PostsAssertUtil.class);

	    /**
	     * Asserts post creation fields for a single POST request.
	     */
	 public static void assertPostCreated(Response response, int expectedUserId, String expectedTitle, String expectedBody) {
		    // Assert the status code is 201 (Created) for a POST request
		    AssertUtil.assertStatusCode(response, 201);

		    // Assert the "data" field is not null
		    AssertUtil.assertFieldNotNull(response, "data");

		    // Assert the required fields in "data"
		    AssertUtil.assertResponseHasField(response, "data.id");
		    AssertUtil.assertResponseHasField(response, "data.user_id");
		    AssertUtil.assertResponseHasField(response, "data.title");
		    AssertUtil.assertResponseHasField(response, "data.body");

		    // Validate the field types (example: id and user_id should be integers, title and body should be strings)
		    AssertUtil.assertFieldIsInteger(response, "data.id");
		    AssertUtil.assertFieldIsInteger(response, "data.user_id");
		    AssertUtil.assertFieldIsString(response, "data.title");
		    AssertUtil.assertFieldIsString(response, "data.body");

		    // Ensure the values returned match the expected ones
		    AssertUtil.assertFieldEquals(response, "data.user_id", expectedUserId);
		    AssertUtil.assertFieldEquals(response, "data.title", expectedTitle);
		    AssertUtil.assertFieldEquals(response, "data.body", expectedBody);
		    AssertUtil.assertPostsJsonSchema(response, "src/test/resources/schemas/postsSchema.json");
	        AssertUtil.assertResponseTimeLessThan(response, 3000);

		    logger.info("✅ POST created and validated successfully.");
		}


	 public static void assertGetAllPosts(Response response) {
		    logger.debug("Asserting GET all posts response...");

		    // Assert status code is 200 (OK)
		    AssertUtil.assertStatusCode(response, 200);

		    // Assert the "data" field is not null and contains posts
		    AssertUtil.assertResponseHasField(response, "data");
		    List<?> posts = response.jsonPath().getList("data");
		    AssertUtil.assertArrayNotEmpty(posts);

		    // Loop through each post and assert the fields
		    for (int i = 0; i < posts.size(); i++) {
		        String postJsonPath = "data[" + i + "]";  // Construct the JSON path for each post

		        // Assert that the post has the required fields
		        AssertUtil.assertResponseHasField(response, postJsonPath + ".id");
		        AssertUtil.assertResponseHasField(response, postJsonPath + ".user_id");
		        AssertUtil.assertResponseHasField(response, postJsonPath + ".title");
		        AssertUtil.assertResponseHasField(response, postJsonPath + ".body");

		        // Assert the field types
		        AssertUtil.assertFieldIsInteger(response, postJsonPath + ".id");
		        AssertUtil.assertFieldIsInteger(response, postJsonPath + ".user_id");
		        AssertUtil.assertFieldIsString(response, postJsonPath + ".title");
		        AssertUtil.assertFieldIsString(response, postJsonPath + ".body");
		     //   AssertUtil.assertPostsJsonSchema(response, "src/test/resources/schemas/postsSchema.json");
		        AssertUtil.assertResponseTimeLessThan(response, 3000);
		    }

		    logger.info("✅ GET all posts validated successfully.");
		}

	// public static void assertGetPostById(Response response, int expectedPostId, String expectedTitle, String expectedBody) {
		 public static void assertGetPostById(Response response, int expectedPostId) {
			    AssertUtil.assertStatusCode(response, 200);
			    AssertUtil.assertFieldEquals(response, "data.id", expectedPostId);
			    AssertUtil.assertResponseHasField(response, "data.title");
			    AssertUtil.assertResponseHasField(response, "data.body");
			    AssertUtil.assertFieldIsInteger(response, "data.user_id");
			    AssertUtil.assertPostsJsonSchema(response, "src/test/resources/schemas/postsSchema.json");
			  //  AssertUtil.assertPostsJsonSchema(response, "src/test/resources/schemas/postsSchema.json");
		        AssertUtil.assertResponseTimeLessThan(response, 3000);
			    logger.info("✅ GET post by ID validated successfully.");
			}

public static void assertPostUpdated(Response response, int expectedUserId, String expectedTitle, String expectedBody) {
    // Assert the status code is 201 (Created) for a POST request
    AssertUtil.assertStatusCode(response, 200);

    // Assert the "data" field is not null
    AssertUtil.assertFieldNotNull(response, "data");

    // Assert the required fields in "data"
    AssertUtil.assertResponseHasField(response, "data.id");
    AssertUtil.assertResponseHasField(response, "data.user_id");
    AssertUtil.assertResponseHasField(response, "data.title");
    AssertUtil.assertResponseHasField(response, "data.body");

    // Validate the field types (example: id and user_id should be integers, title and body should be strings)
    AssertUtil.assertFieldIsInteger(response, "data.id");
    AssertUtil.assertFieldIsInteger(response, "data.user_id");
    AssertUtil.assertFieldIsString(response, "data.title");
    AssertUtil.assertFieldIsString(response, "data.body");

    // Ensure the values returned match the expected ones
    AssertUtil.assertFieldEquals(response, "data.user_id", expectedUserId);
    AssertUtil.assertFieldEquals(response, "data.title", expectedTitle);
    AssertUtil.assertFieldEquals(response, "data.body", expectedBody);
    AssertUtil.assertPostsJsonSchema(response, "src/test/resources/schemas/postsSchema.json");
    AssertUtil.assertResponseTimeLessThan(response, 3000);
    logger.info("✅ POST created and validated successfully.");
}


public static void assertPostPatched(Response response, int expectedUserId, String expectedTitle, String expectedBody) {
    // Assert the status code is 201 (Created) for a POST request
    AssertUtil.assertStatusCode(response, 200);

    // Assert the "data" field is not null
    AssertUtil.assertFieldNotNull(response, "data");

    // Assert the required fields in "data"
    AssertUtil.assertResponseHasField(response, "data.id");
    AssertUtil.assertResponseHasField(response, "data.user_id");
    AssertUtil.assertResponseHasField(response, "data.title");
    AssertUtil.assertResponseHasField(response, "data.body");

    // Validate the field types (example: id and user_id should be integers, title and body should be strings)
    AssertUtil.assertFieldIsInteger(response, "data.id");
    AssertUtil.assertFieldIsInteger(response, "data.user_id");
    AssertUtil.assertFieldIsString(response, "data.title");
    AssertUtil.assertFieldIsString(response, "data.body");

    // Ensure the values returned match the expected ones
    AssertUtil.assertFieldEquals(response, "data.user_id", expectedUserId);
    AssertUtil.assertFieldEquals(response, "data.title", expectedTitle);
    AssertUtil.assertFieldEquals(response, "data.body", expectedBody);
    AssertUtil.assertPostsJsonSchema(response, "src/test/resources/schemas/postsSchema.json");
    AssertUtil.assertResponseTimeLessThan(response, 3000);
    logger.info("✅ POST created and validated successfully.");
}
}