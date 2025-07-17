package com.gorest.tests.delete.positive;

import com.gorest.base.BaseTest;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.AssertUtil;
import com.gorest.utils.TestDataManager;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class DeleteCommentByCommentId extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DeleteCommentByCommentId.class);

    @Test
    public void testDeleteCommentById() {
        int commentId = TestDataManager.getCommentId(); // ✅ Make sure this was set in previous POST comment

        if (commentId == 0) {
            throw new RuntimeException("❌ Comment ID is not set. Ensure a comment was created first.");
        }

        logger.info("🗑️ Deleting comment with ID: {}", commentId);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", commentId)
                .when()
                .delete(APIEndpoints.COMMENT_BY_ID)
                .then()
                .log().all()
                .extract().response();

        AssertUtil.assertStatusCode(response, 204);
        logger.info("✅ Comment deleted successfully: {}", commentId);
    }
}
