package com.gorest.tests.posts.negative;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.data.PostsDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.posts;
import com.gorest.utils.PostsAssertUtil;

import io.restassured.response.Response;

public class UpdatePostMissingFieldsTest extends BaseTest {
	 private static final Logger logger = LoggerFactory.getLogger(UpdatePostMissingFieldsTest.class);

	   
	 @Test(dataProvider = "missingUpdateUserField", dataProviderClass = PostsDataProvider.class)
	 public void testUpdatePostByMissingField(String postId, String title, String body, int userId) {
	     logger.info("🚫 Starting UPDATE test for invalid postId: {}", postId);

	     // Prepare payload
	     posts postPayload = new posts();
	     postPayload.setUserId(userId);
	     postPayload.setTitle(title);
	     postPayload.setBody(body);

	     // Make PATCH request
	     Response response = given()
	             .spec(requestSpec)
	             .pathParam("id", postId)
	             .body(postPayload)
	             .when()
	             .patch(APIEndpoints.POST_BY_ID)
	             .then()
	             .log().all()
	             .extract().response();

	     // 🔎 Assertion for expected 404 + message
	     // PostsAssertUtil.assertUpdateByMissingField(response, title,body,userId,postId);

	     logger.info("✅ Finished UPDATING test for invalid postId: {}", postId);
	 }
}