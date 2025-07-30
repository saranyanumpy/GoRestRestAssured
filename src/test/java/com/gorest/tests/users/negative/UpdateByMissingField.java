package com.gorest.tests.users.negative;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.gorest.base.BaseTest;
import com.gorest.data.UserDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.UserAssertUtil;

import io.restassured.response.Response;

public class UpdateByMissingField extends BaseTest{
	private static final Logger logger = LoggerFactory.getLogger(UpdateByMissingField.class);

    @Test(dataProvider = "updateInvalidEmail", dataProviderClass = UserDataProvider.class)
    public void testCreateUser(String userId,String name, String email, String gender, String status) {
    			logger.info("Starting Invalid Missing Fields...");
    	      //  logger.info("🔍 Testing valid user ID: {}", TestDataManager.getUserId());
    	     //   int invalidId = TestDataManager.getUserId() + 9999;
    	        Response response = given()
    	                .spec(requestSpec)
    	                .pathParam("id", userId)
    	                .when()
    	                .get(APIEndpoints.USER_BY_ID)
    	                .then()
    	                .log().all()
    	                .extract().response();

    	        UserAssertUtil.assertInvalidEmail(response, userId, name,email,gender,status);
    	       
    	        logger.info("Invalid Missing Fields handled correctly.");
    	    }
    	}

