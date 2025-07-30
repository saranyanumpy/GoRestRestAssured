package com.gorest.tests.posts.positive;

import com.gorest.base.BaseTest;
import com.gorest.data.PostsDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.posts;
import com.gorest.utils.PostsAssertUtil;
import com.gorest.utils.TestDataManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PostsWorkflowTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(PostsWorkflowTest.class);

    private int userId;
    private int postId;
    private String label;
    private String title;
    private String body;

    @BeforeClass
    public void setupUser() {
        userId = TestDataManager.getUserId();
        logger.info("📌 Retrieved userId: {}", userId);
    }

    @Test(dataProvider = "createPostsData", dataProviderClass = PostsDataProvider.class)
    public void testCreatePost(String label, String title, String body) {
        this.label = label;
        this.title = title;
        this.body = body;

        logger.info("✅ Starting CreatePost test for label: {}", label);

        posts postsPayload = new posts();
        postsPayload.setUserId(userId);
        postsPayload.setTitle(title);
        postsPayload.setBody(body);

        Response response = given()
                .spec(requestSpec)
                .body(postsPayload)
                .when()
                .post(APIEndpoints.POSTS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();

        // ✅ Validate POST success
        PostsAssertUtil.assertPostCreated(response, userId, title, body);

        // ✅ Get and store postId for all labels
        postId = response.jsonPath().getInt("data.id");
        TestDataManager.storePostId(label, postId);
        logger.info("💾 Stored postId = {} with label = {}", postId, label);

        // ✅ Special storage for Comments module
        if ("firstPost".equals(label)) {
            TestDataManager.setCommentPostId(postId);
            TestDataManager.setPostBody(body);
            logger.info("💾 Stored commentPostId = {} for Comments module", postId);
        }

        logger.info("📌 Post created: ID = {}, label = {}, body = {}", postId, label, body);
    }
    @Test(dependsOnMethods = "testCreatePost")
    public void testUpdatePost() {
        logger.info("✅ Starting UpdatePost test for label: {}", label);

        String updatedTitle = "Updated " + title;
        String updatedBody = "Updated " + body;

        posts postsPayload = new posts();
        postsPayload.setUserId(userId);
        postsPayload.setTitle(updatedTitle);
        postsPayload.setBody(updatedBody);

        // ✅ Always retrieve postId by label from ThreadLocal map
        postId = TestDataManager.getPostId(label);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", postId)
                .body(postsPayload)
                .when()
                .put(APIEndpoints.POST_BY_ID)
                .then()
                .log().all()
                .extract().response();

        PostsAssertUtil.assertPostUpdated(response, userId, updatedTitle, updatedBody);

        logger.info("📌 Post updated successfully for ID = {}", postId);
    }

    @Test(dependsOnMethods = "testUpdatePost")
    public void testPatchPost() {
        logger.info("✅ Starting PatchPost test for label: {}", label);

        String patchedTitle = "Patched " + title;
        String patchedBody = "Patched " + body;

        posts postsPayload = new posts();
        postsPayload.setUserId(userId);
        postsPayload.setTitle(patchedTitle);
        postsPayload.setBody(patchedBody);

        // ✅ Retrieve postId from ThreadLocal map using label
        postId = TestDataManager.getPostId(label);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", postId)
                .body(postsPayload)
                .when()
                .patch(APIEndpoints.POST_BY_ID)
                .then()
                .log().all()
                .extract().response();

        PostsAssertUtil.assertPostPatched(response, userId, patchedTitle, patchedBody);

        logger.info("📌 Post patched successfully for ID = {}", postId);
    }


    @Test(dependsOnMethods = "testPatchPost")
    public void testGetPostById() {
        logger.info("✅ Starting GetPostById test...");

        for (String labelToValidate : new String[]{"firstPost", "secondPost"}) {
            if (!TestDataManager.hasPostId(labelToValidate)) {
                logger.warn("⚠️ Skipping GetById for label '{}' — postId not found", labelToValidate);
                continue;
            }

            int id = TestDataManager.getPostId(labelToValidate);
            logger.info("🔍 Validating GET for label '{}', postId = {}", labelToValidate, id);

            Response response = given()
                    .spec(requestSpec)
                    .pathParam("id", id)
                    .when()
                    .get(APIEndpoints.POST_BY_ID)
                    .then()
                    .log().all()
                    .extract().response();

            PostsAssertUtil.assertGetPostById(response, id);
        }

        logger.info("✅ GetPostById test completed successfully for all available posts.");
    }


}
