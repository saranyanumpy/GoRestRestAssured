package com.gorest.tests.users.negative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.data.UserDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.UserAssertUtil;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class GetByInvalidUserId extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(CreateUserInvalidEmail.class);

    @Test(dataProvider = "invalidUserId", dataProviderClass = UserDataProvider.class)
    public void testCreateUser(String userId) {
    			logger.info("Starting Invalid user ID ...");
    	        logger.info("🔍 Testing invalid user ID: {}", userId);

    	        Response response = given()
    	                .spec(requestSpec)
    	                .pathParam("id", userId)
    	                .when()
    	                .get(APIEndpoints.USER_BY_ID)
    	                .then()
    	                .log().all()
    	                .extract().response();

    	        UserAssertUtil.assertGetByInvalidUserId(response, userId);
    	       
    	        logger.info("Invalid user ID handled correctly.");
    	    }
    	}
