package com.api.tests;

import static org.hamcrest.Matchers.oneOf;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class TC05_CREATE_USER_POST_API_TESTNG_PARAMETERS {
	
	@Parameters({"name", "email", "gender", "status"})
	@Test
	public void createUserPostAPITestNGParameters(String name, String email, String gender, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("email", email);
		requestBody.put("gender", gender);
		requestBody.put("status", status);
		
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
	
		.when()
			.body(requestBody.toString())
			.post("https://gorest.co.in/public/v2/users")
	
		.then()
			.statusCode(201)
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
}