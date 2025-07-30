package com.gorest.tests.posts.negative;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.posts;
import com.gorest.models.users;
import static io.restassured.RestAssured.given;
import com.gorest.data.PostsDataProvider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.response.Response;

public class CreatePostInvalidUserIdTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CreatePostInvalidUserIdTest.class);

    @Test(dataProvider = "createPostInvalidUserId", dataProviderClass = PostsDataProvider.class)
    public void testCreateUser(String title, String body, int userId) {
        logger.info("Starting User Invalid test...");
     //   int userId = TestDataManager.getUserId();

        // ✅ Build updated user payload
        posts postsPayload = new posts();
        postsPayload.setUserId(userId);
        postsPayload.setTitle(title);
        postsPayload.setBody(body);

        // ✅ Send PUT request to update user
        Response response = given()
                .spec(requestSpec)
                .body(postsPayload)
                .when()
                .post(APIEndpoints.POSTS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();
     
        Assert.assertEquals(response.getStatusCode(), 422, "Expected 422 Unprocessable Entity");

        // ✅ Optionally check message in response
        String responseBody = response.asString().toLowerCase();
        Assert.assertTrue(responseBody.contains("user") || responseBody.contains("invalid"), "Expected user-related validation error");

        logger.info("✅ Finished test for invalid userId.");
    }
}
  