package com.gorest.tests.users.positive;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.data.UserDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.users;
import com.gorest.utils.TestDataManager;
import com.gorest.utils.UserAssertUtil;

import io.restassured.response.Response;

public class PatchUserTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserTest.class); // ✅ Corrected logger

    @Test(dataProvider = "patchUserData", dataProviderClass = UserDataProvider.class)
    public void testPatchUser(String name, String email, String gender, String status) {
        logger.info("🔁 Starting Patch User test...");

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
                .patch(APIEndpoints.USER_BY_ID)
                .then()
                .log().all()
                .extract().response();

        // ✅ Validate response
        UserAssertUtil.assertUserPatch(response, name, email, gender, status);

        // ✅ Optionally update shared data
        TestDataManager.setUserName(name);
        TestDataManager.setUserEmail(email);

        logger.info("✅ User updated successfully with ID: {}", userId);
        logger.info("✅ Finished UpdateUser test.");
    }
}

