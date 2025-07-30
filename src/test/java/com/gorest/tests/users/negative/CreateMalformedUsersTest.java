package com.gorest.tests.users.negative;

import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.tests.users.positive.CreateUserTest;
import com.gorest.utils.FileUtil;
import com.gorest.utils.NegativeUserAssertUtil;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CreateMalformedUsersTest extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(CreateMalformedUsersTest.class);
	@Test(dataProvider = "malformedJsonFiles", dataProviderClass = com.gorest.data.UserMalformedDataProvider.class)
	public void testMalformedJson(String fileName) {
		logger.info("Starting Malformed JSON User test...");
	    String malformedBody = FileUtil.readFileFromResources(fileName);

	    Response response = given()
	            .spec(requestSpec)
	            .body(malformedBody)
	            .when()
	            .post(APIEndpoints.USERS_ENDPOINT)
	            .then()
	            .log().all()
	            .extract().response();
	    logger.info("Request: {}",  malformedBody);
	    System.out.println("Status Code: " + response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());

	 NegativeUserAssertUtil.assertMalformedJsonResponse(response);
	 logger.info("Finished Malformed JSON User test...");
	}
}