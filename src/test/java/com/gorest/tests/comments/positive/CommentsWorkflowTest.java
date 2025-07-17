package com.gorest.tests.comments.positive;

import com.gorest.base.BaseTest;
import com.gorest.data.CommentsDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.comments;
import com.gorest.utils.TestDataManager;
import com.gorest.utils.CommentsAssertUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommentsWorkflowTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CommentsWorkflowTest.class);

    private int postId;
    private int commentId;
    private String name;
    private String email;
    private String body;

    @BeforeClass
    public void setupData() {
        logger.info("✅ Starting CreateCOMMENT test for label: ");

        // ✅ Get postId from previous post (label: "firstPost")
        postId = TestDataManager.getCommentPostId();
        logger.info("💾 Sara Stored commentPostId = {}", postId);
        // ❌ Fail fast if postId wasn't stored properly
        if (postId == 0) {
            throw new RuntimeException("❌ postId for comments is 0. Make sure 'firstPost' is created and stored in TestDataManager.");
        }
     // ✅ Get comment data from TestDataManager
        name = TestDataManager.getUserName();   // Assumes set during user creation
        email = TestDataManager.getUserEmail(); // Same as above
        body = TestDataManager.getPostBody();   // From the original post content

        logger.info("📌 Retrieved postId = {}, name = {}, email = {}, body = {}", postId, name, email, body);
    }
@Test
    public void testCreateComment() {
        logger.info("➡️ Creating comment for post ID: {}", postId);

        comments commentPayload = new comments();
        commentPayload.setPostId(postId);
        commentPayload.setName(name);
        commentPayload.setEmail(email);
        commentPayload.setBody(body);

        String fullUrl = RestAssured.baseURI + APIEndpoints.COMMENTS_ENDPOINT;
        logger.info("🌐 Full URL: {}", fullUrl);
        logger.info("Comment full: postId={}, name={}, email={}, body={}", postId, name, email, body);

        Response response = given()
                .spec(requestSpec)
                .body(commentPayload)
                .when()
                .post(APIEndpoints.COMMENTS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();

        CommentsAssertUtil.assertCommentCreated(response, postId, name, email, body);

        commentId = response.jsonPath().getInt("data.id");
        TestDataManager.setCommentId(commentId);
        logger.info("✅ Comment created with ID: {}", commentId);
    }

    @Test(dependsOnMethods = "testCreateComment")
    public void testGetCommentById() {
        logger.info("🔍 Retrieving comment ID: {}", commentId);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", commentId)
                .when()
                .get(APIEndpoints.COMMENT_BY_ID)
                .then()
                .log().all()
                .extract().response();

        CommentsAssertUtil.assertGetCommentById(
                response,
                commentId,
                postId,
                name,
                email,body
  //              TestDataManager.getCommentBody()
        );

        logger.info("✅ Retrieved and validated comment ID: {}", commentId);
    }
    
    @Test(dependsOnMethods = "testGetCommentById", dataProvider = "UpdateCommentTest", dataProviderClass = CommentsDataProvider.class)
    public void testUpdateComment(String updatedName, String updatedEmail, String updatedBody) {
        logger.info("✏️ Updating comment ID: {}", commentId);

        comments updatePayload = new comments();
        updatePayload.setPostId(postId); // required for PUT
        updatePayload.setName(updatedName);
        updatePayload.setEmail(updatedEmail);
        updatePayload.setBody(updatedBody);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", commentId)
                .body(updatePayload)
                .when()
                .put(APIEndpoints.COMMENT_BY_ID)
                .then()
                .log().all()
                .extract().response();

        CommentsAssertUtil.assertUpdateCommentById(response, postId, updatedName, updatedEmail, updatedBody);
        logger.info("✅ Comment updated successfully");
    }
    @Test(dependsOnMethods = "testUpdateComment", dataProvider = "PatchCommentTest", dataProviderClass = CommentsDataProvider.class)
    public void testPatchComment(String patchedName, String patchedEmail, String patchedBody) {
        logger.info("✏️ Patching comment ID: {}", commentId);

        comments updatePayload = new comments();
        updatePayload.setPostId(postId); // required for PUT
        updatePayload.setName(patchedName);
        updatePayload.setEmail(patchedEmail);
        updatePayload.setBody(patchedBody);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", commentId)
                .body(updatePayload)
                .when()
                .put(APIEndpoints.COMMENT_BY_ID)
                .then()
                .log().all()
                .extract().response();

        CommentsAssertUtil.assertPatchCommentById(response, postId, patchedName, patchedEmail, patchedBody);
        logger.info("✅ Comment updated successfully");
    }

    
}
