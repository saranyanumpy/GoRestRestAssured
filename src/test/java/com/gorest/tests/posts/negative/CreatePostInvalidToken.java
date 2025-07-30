package com.gorest.tests.posts.negative;

import static io.restassured.RestAssured.given;

import com.gorest.config.ConfigLoader;
import com.gorest.data.PostsDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.posts;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatePostInvalidToken {

	 private static final Logger logger = LoggerFactory.getLogger(CreatePostInvalidUserIdTest.class);

	    @Test(dataProvider = "createPostInvalidUserId", dataProviderClass = PostsDataProvider.class)
	    public void testCreateUser(String title, String body, int userId) {
	        logger.info("Starting User Invalid test...");
	     //   int userId = TestDataManager.getUserId();

        String baseUrl = ConfigLoader.getInstance().getConfig().getBaseUrl();


        // Dummy data
//        String title = "Unauthorized Post Title";
//        String body = "This is a post attempt using an invalid token.";
//        int userId = 9999; // Dummy user ID

        posts postsPayload = new posts();
        postsPayload.setUserId(userId);
        postsPayload.setTitle(title);
        postsPayload.setBody(body);

        Response response = given()
                .baseUri(baseUrl)
                .basePath(APIEndpoints.POSTS_ENDPOINT)
                .header("Authorization", "Bearer invalid_token_123")  // ❗ Fake token
                .contentType("application/json")
                .body(postsPayload)
                .log().all()
                .when()
                .post()
                .then()
                .log().all()
                .extract().response();

        // Assert 401 Unauthorized
        Assert.assertEquals(response.getStatusCode(), 401, "❌ Expected 401 Unauthorized");
        Assert.assertTrue(response.asString().contains("Invalid token"), "❌ Expected 'Invalid token' error message");

        logger.info("✅ Finished CreatePost Invalid Token Test.");
    }
}
