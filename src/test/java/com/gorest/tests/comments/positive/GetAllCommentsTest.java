
	package com.gorest.tests.comments.positive;

	import com.gorest.base.BaseTest;
	import com.gorest.utils.CommentsAssertUtil;
	import io.restassured.response.Response;

	import org.testng.annotations.Test;

	import static io.restassured.RestAssured.given;
	import com.gorest.endpoints.APIEndpoints;

	public class GetAllCommentsTest extends BaseTest {

	    @Test
	    public void testGetAllComments() {
	        Response response = given()
	                .spec(requestSpec)
	                .when()
	                .get(APIEndpoints.COMMENTS_ENDPOINT)
	                .then()
	                .log().all()
	                .extract().response();

	        CommentsAssertUtil.assertGetAllComments(response);
	    }
	}

