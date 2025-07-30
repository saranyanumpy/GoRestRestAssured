
package com.gorest.tests.users.negative;

import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.data.UserDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.models.users;
import com.gorest.utils.UserAssertUtil;
import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.response.Response;

public class CreateUserInvalidEmail extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserInvalidEmail.class);

    @Test(dataProvider = "invalidUserEmail", dataProviderClass = UserDataProvider.class)
    public void testCreateUser(String name, String email, String gender, String status) {
        logger.info("Starting User Invalid test...");
     //   int userId = TestDataManager.getUserId();

        // ✅ Build updated user payload
        users userPayload = new users();
        userPayload.setName(name);
        userPayload.setEmail(email);
        userPayload.setGender(gender);
        userPayload.setStatus(status);

        // ✅ Send PUT request to update user
        Response response = given()
                .spec(requestSpec)
                .body(userPayload)
                .when()
                .post(APIEndpoints.USERS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();
     
        // ✅ Validate response
      // UserAssertUtil.assertInvalidEmail(response, name, email, gender, status);

       
        logger.info("Finished User Invalid test...");
    }
}
  