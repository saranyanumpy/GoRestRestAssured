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

public class DeleteUserByUserId extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserByUserId.class);

    @Test
    public void testDeleteUserById() {
        int userId = TestDataManager.getUserId(); // Ensure this was set when user was created

        if (userId == 0) {
            throw new RuntimeException("❌ userId is missing. Ensure user was created and stored before running delete.");
        }

        logger.info("🗑️ Deleting user with ID: {}", userId);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .when()
                .delete(APIEndpoints.USER_BY_ID)
                .then()
                .log().all()
                .extract().response();

        AssertUtil.assertStatusCode(response, 204);
        logger.info("✅ User deleted successfully with ID: {}", userId);
    }
}
