package com.gorest.tests.users.positive;

import com.gorest.base.BaseTest;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.AssertUtil;
import com.gorest.utils.UserAssertUtil;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

public class GetAllUsersTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GetAllUsersTest.class);

    @Test
    public void testGetAllUsers() {
        logger.info("Starting GetAllUsers test...");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get(APIEndpoints.USERS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();

        // ✅ Status Code Assertion
        UserAssertUtil.assertGetAllUsers(response);

        // ✅ User List Size
        List<Map<String, Object>> users = response.jsonPath().getList("data");
        int userCount = users.size();
        logger.info("✅ Total users returned: {}", userCount);
        System.out.println("✅ Total users returned: " + userCount);

        if (userCount == 0) {
            logger.warn("⚠️ No users found in the response.");
            System.out.println("⚠️ No users found in the response.");
        }

        // ✅ Field Validations on first 5 users
        for (int i = 0; i < Math.min(users.size(), 5); i++) {
            Map<String, Object> user = users.get(i);
            AssertUtil.assertNotNull(user.get("id"), "User ID should not be null");
            AssertUtil.assertNotNull(user.get("name"), "User name should not be null");
            AssertUtil.assertNotNull(user.get("email"), "User email should not be null");
            AssertUtil.assertNotNull(user.get("gender"), "User gender should not be null");
            AssertUtil.assertNotNull(user.get("status"), "User status should not be null");
        }

        logger.info("Successfully validated GetAllUsers response.");
        logger.info("Finished GetAllUsers test.");
    }
}
