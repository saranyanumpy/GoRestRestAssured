package com.gorest.tests.posts.positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.PostsAssertUtil;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class GetAllPostsTest extends BaseTest {
	 private static final Logger logger = LoggerFactory.getLogger(PostsAssertUtil.class);

	 @Test
	 public void testGetAllPosts() {
		 logger.info("Starting GetAllPosts test...");
		 Response response= given()
				 .spec(requestSpec)
				 .when()
				 .get(APIEndpoints.POSTS_ENDPOINT)
				 .then()
				 .log().all()
				 .extract().response();
		 logger.info("Successfully validated GetAllUsers response.");
	        logger.info("Finished GetAllUsers test.");			 
				 
				 
	 }
}
