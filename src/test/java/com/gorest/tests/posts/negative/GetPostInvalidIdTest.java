package com.gorest.tests.posts.negative;

import static io.restassured.RestAssured.given;

import com.gorest.base.BaseTest;
import com.gorest.data.PostsDataProvider; // ✅ Use correct provider for posts
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.PostsAssertUtil;  // ✅ Create this if not already
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class GetPostInvalidIdTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GetPostInvalidIdTest.class);

    @Test(dataProvider = "invalidPostId", dataProviderClass = PostsDataProvider.class)
    public void testGetPostByInvalidId(String postId) {
        logger.info("🚫 Starting test for invalid postId: {}", postId);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", postId)   // ✅ safe if postId is a clean string
                .when()
                .get(APIEndpoints.POST_BY_ID)
                .then()
                .log().all()
                .extract().response();

        PostsAssertUtil.assertGetByInvalidPostId(response, postId);
    }
}
