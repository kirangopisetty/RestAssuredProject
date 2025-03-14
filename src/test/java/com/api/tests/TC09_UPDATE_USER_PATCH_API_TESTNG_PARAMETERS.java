package com.api.tests;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.oneOf;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class TC09_UPDATE_USER_PATCH_API_TESTNG_PARAMETERS {
	
	@Parameters({"name", "email", "status"})
	@Test
	public void updateUserPatchAPITestNGParameters(String name, String email, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("email", email);
		requestBody.put("status", status);
		
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
	
		.when()
			.body(requestBody.toString())
			.patch("https://gorest.co.in/public/v2/users/7767290")
	
		.then()
			.log().body()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.time(greaterThan(100L))
			.time(lessThan(3000L))
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"));
	}
}