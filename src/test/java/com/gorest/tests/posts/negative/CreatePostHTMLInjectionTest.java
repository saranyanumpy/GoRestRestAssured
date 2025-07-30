package com.gorest.tests.posts.negative;

import com.gorest.base.BaseTest;
import com.gorest.data.PostsDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.posts;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreatePostHTMLInjectionTest extends BaseTest {

	 private static final Logger logger = LoggerFactory.getLogger(CreatePostInvalidUserIdTest.class);

	 @Test(dataProvider = "createPostInvalidUserId", dataProviderClass = PostsDataProvider.class)
	 public void testCreateUser(String title, String body, int userId) {
	     logger.info("Starting HTML Injection... userId: {}", userId);

	     posts postsPayload = new posts();
	     postsPayload.setUserId(userId);  // Still needed if your model maps it
	     postsPayload.setTitle(title);
	     postsPayload.setBody(body);

	     Response response = given()
	             .spec(requestSpec)
	             .body(postsPayload)
	             .pathParam("id", userId)
	             .when()
	             .post(APIEndpoints.POSTS_ENDPOINT)  // ✅ FIXED
	             .then()
	             .log().all()
	             .extract().response();

	     int statusCode = response.getStatusCode();
	     String responseBody = response.asString().toLowerCase();

	     Assert.assertTrue(statusCode == 201 || statusCode == 422,
	             "Expected status code 201 (created) or 422 (validation failed)");

	     if (statusCode == 201) {
	         Assert.assertFalse(responseBody.contains("<script>"), "Response should not reflect raw <script> HTML");
	     } else if (statusCode == 422) {
	         Assert.assertTrue(responseBody.contains("title") || responseBody.contains("invalid"),
	                 "Expected validation error for HTML/script input");
	     }

	     logger.info("✅ Finished HTML Injection Test.");
	 }
}