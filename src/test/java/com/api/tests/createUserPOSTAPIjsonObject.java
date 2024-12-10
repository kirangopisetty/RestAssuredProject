package com.api.tests;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class createUserPOSTAPIjsonObject {
	
	@Test
	public void createUserPostAPIjsonObject() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Postman");	// creating a variable called name & assigning a value Postman to it
		requestBody.put("gender", "male");
		requestBody.put("email", "postman@assured.com");
		requestBody.put("status", "inactive");
		
		given().
			header("Accept", "application/json").
			header("Content-Type", "application/json").
			header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
		
		when().
			body(requestBody.toString()).
			post("https://gorest.co.in/public/v2/users").
		
		then().
			statusCode(201).
			header("Content-Type", "application/json; charset=utf-8").
			log().all();
	}
}
