package com.gorest.tests.posts.negative;

import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.data.PostsDataProvider;
import com.gorest.data.UserDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.posts;
import com.gorest.models.users;
import com.gorest.utils.PostsAssertUtil;
import com.gorest.utils.UserAssertUtil;
import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.response.Response;

public class CreatePostMissingFieldsTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(CreatePostMissingFieldsTest.class);

	@Test(dataProvider = "missingUserField", dataProviderClass = PostsDataProvider.class)
	public void testCreatePostsMissingField(String title, String body, int userId) {
	    logger.info("Starting Missing Name User test...");

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

	    // ✅ Validate response for missing name (expecting a 422 or error response)
	    PostsAssertUtil.assertMissingField(response, title, body,userId);

	    logger.info("Finished Missing Name User test.");
	}
}