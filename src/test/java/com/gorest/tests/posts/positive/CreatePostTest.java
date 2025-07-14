package com.gorest.tests.posts.positive;

import org.testng.annotations.Test;
import com.gorest.base.BaseTest;
import com.gorest.data.PostsDataProvider;

import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.posts;
import com.gorest.utils.TestDataManager;
import com.gorest.utils.PostsAssertUtil;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.response.Response;

public class CreatePostTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CreatePostTest.class);
   
    @Test(dataProvider = "createPostsData",dataProviderClass=PostsDataProvider.class)
    public void testCreatePost(String label, String title, String body) {
        logger.info("✅ Starting CreatePost test for label: {}", label);

        int userId = TestDataManager.getUserId();
        logger.info("Using user ID: {}", userId);

        posts postsPayload = new posts();
        postsPayload.setUserId(userId);
        postsPayload.setTitle(title);
        postsPayload.setBody(body);

        logger.info("➡️ Payload: {}", postsPayload);

        Response response = given()
                .spec(requestSpec)
                .body(postsPayload)
                .when()
                .post(APIEndpoints.POSTS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();

        PostsAssertUtil.assertPostCreated(response, userId, title, body);

        int postId = response.jsonPath().getInt("data.id");
        TestDataManager.storePostId(label, postId);

        logger.info("📌 Post created: ID = {}, stored with label = {}", postId, label);
        logger.info("✅ Finished CreatePost test for label: {}", label);
    }

}
