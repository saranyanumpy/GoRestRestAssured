
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

public class CreateUserMissingName extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(CreateUserMissingName.class);

	@Test(dataProvider = "missingUserName", dataProviderClass = UserDataProvider.class)
	public void testCreateUser(String name, String email, String gender, String status) {
	    logger.info("Starting Missing Name User test...");

	    // ✅ Build the user payload with missing name
	    users userPayload = new users();
	    userPayload.setName(name);  // Name is missing in the data provider, so this will be null or empty
	    userPayload.setEmail(email);
	    userPayload.setGender(gender);
	    userPayload.setStatus(status);

	    // ✅ Send POST request to create user with missing name
	    Response response = given()
	            .spec(requestSpec)
	            .body(userPayload)
	            .when()
	            .post(APIEndpoints.USERS_ENDPOINT)  // Assuming POST is used for user creation
	            .then()
	            .log().all()
	            .extract().response();

	    // ✅ Validate response for missing name (expecting a 422 or error response)
	    UserAssertUtil.assertMissingUserName(response, email, gender, status);

	    logger.info("Finished Missing Name User test.");
	}
}