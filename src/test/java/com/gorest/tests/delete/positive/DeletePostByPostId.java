
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

public class DeletePostByPostId extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DeletePostByPostId.class);

    @Test
    public void testDeletePostById() {
        String label = "firstPost";  // Make sure this matches how you stored it earlier
        Integer postId = TestDataManager.getPostId(label);

        if (postId == null || postId == 0) {
            throw new RuntimeException("❌ postId is missing. Ensure the post with label '" + label + "' was created and stored properly.");
        }

        logger.info("🗑️ Deleting post with ID: {} (label: {})", postId, label);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", postId)
                .when()
                .delete(APIEndpoints.POST_BY_ID)
                .then()
                .log().all()
                .extract().response();

        AssertUtil.assertStatusCode(response, 204);
        logger.info("✅ Post deleted successfully with ID: {}", postId);
    }
}
