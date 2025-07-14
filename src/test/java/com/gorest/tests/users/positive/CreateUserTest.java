	package com.gorest.tests.users.positive;
	
	import org.testng.annotations.Test;
	
	import com.gorest.base.BaseTest;
	import com.gorest.data.UserDataProvider;
	import com.gorest.endpoints.APIEndpoints;
	import com.gorest.models.users;
	import com.gorest.utils.RandomDataGenerator;
	import com.gorest.utils.TestDataManager;
	import com.gorest.utils.UserAssertUtil;
	import static io.restassured.RestAssured.given;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import io.restassured.response.Response;
	
	public class CreateUserTest extends BaseTest {
	
	    private static final Logger logger = LoggerFactory.getLogger(CreateUserTest.class);
	
	    @Test(dataProvider = "createUserData", dataProviderClass = UserDataProvider.class)
	    public void testCreateUser(String name, String email, String gender, String status) {
	        logger.info("Starting CreateUser test...");
	
	        // ✅ Generate random test data
	        String generatedName = RandomDataGenerator.generateFullName();
	        String generatedEmail = RandomDataGenerator.generateEmail();
	
	        // ✅ Build user payload
	        users userPayload = new users();
	        userPayload.setName(generatedName);
	        userPayload.setEmail(generatedEmail);
	        userPayload.setGender(gender);
	        userPayload.setStatus(status);
	
	        // ✅ Send POST request
	        Response response = given()
	                .spec(requestSpec)
	                .body(userPayload)
	                .when()
	                .post(APIEndpoints.USERS_ENDPOINT)
	                .then()
	                .log().all()
	                .extract().response();
	        logger.info("User created with Saranya");
	        int userId = response.jsonPath().getInt("data.id");
	        TestDataManager.setUserId(userId);
	        TestDataManager.setUserName(generatedName);
	        TestDataManager.setUserEmail(generatedEmail);
	        
	        // ✅ Validate response using reusable assert class
	        UserAssertUtil.assertUserCreated(response, generatedName, generatedEmail, gender, status);
	        logger.info("User created with Saranya");
	        // ✅ Save data to TestDataManager for reuse
	       logger.info("User created with ID: {}", userId);
	
	        logger.info("Finished CreateUser test successfully.");
	    }
	}
