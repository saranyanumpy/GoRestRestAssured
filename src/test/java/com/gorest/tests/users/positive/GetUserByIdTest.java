package com.gorest.tests.users.positive;

import com.gorest.base.BaseTest;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.AssertUtil;
import com.gorest.utils.TestDataManager;
import com.gorest.utils.UserAssertUtil;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class GetUserByIdTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GetUserByIdTest.class);

    @Test
    public void testGetUserById() {
        logger.info("Starting GetUserById test...");

        int userId = TestDataManager.getUserId();
        String expectedName = TestDataManager.getUserName();
        String expectedEmail = TestDataManager.getUserEmail();

        logger.info("Fetching user with ID: {}", userId);

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .when()
                .get(APIEndpoints.USER_BY_ID)   
                .then()
                .log().all()
                .extract().response();

        // ✅ Use reusable assertion method
        UserAssertUtil.assertGetUserById(response, userId, expectedName, expectedEmail);

        logger.info("✅ Successfully validated user fetched by ID.");
        logger.info("Finished GetUserById test.");
    }
}
