package com.gorest.tests.users.positive;

import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.data.UserDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.users;
import com.gorest.utils.RandomDataGenerator;
import com.gorest.utils.UserAssertUtil;
import com.gorest.utils.TestDataManager;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserTest.class); // ✅ Corrected logger

    @Test(dataProvider = "updateUserData", dataProviderClass = UserDataProvider.class)
    public void testUpdateUser(String name, String email, String gender, String status) {
        logger.info("🔁 Starting UpdateUser test...");

        int userId = TestDataManager.getUserId();

        // ✅ Build updated user payload
        users userPayload = new users();
        userPayload.setName(name);
        userPayload.setEmail(email);
        userPayload.setGender(gender);
        userPayload.setStatus(status);

        // ✅ Send PUT request to update user
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .body(userPayload)
                .when()
                .put(APIEndpoints.USER_BY_ID)
                .then()
                .log().all()
                .extract().response();

        // ✅ Validate response
        UserAssertUtil.assertUserUpdated(response, name, email, gender, status);

        // ✅ Optionally update shared data
        TestDataManager.setUserName(name);
        TestDataManager.setUserEmail(email);

        logger.info("✅ User updated successfully with ID: {}", userId);
        logger.info("✅ Finished UpdateUser test.");
    }
}
