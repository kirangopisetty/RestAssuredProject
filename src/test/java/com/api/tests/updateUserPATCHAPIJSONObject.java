package com.api.tests;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class updateUserPATCHAPIJSONObject {

	@Test
	public void updateUserAPI() {
		
	JSONObject requestBody = new JSONObject();
	requestBody.put("name", "JsonObjectPostman");
	requestBody.put("email", "jsonoect@patch.com");
	requestBody.put("status", "active");
		
		given().
			header("Accept", "application/json").
			header("Content-Type", "application/json").
			header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
				
		when().
			body(requestBody.toString()).
			patch("https://gorest.co.in/public/v2/users/7564009").
		
		then().
			statusCode(200).
			header("Content-Type", "application/json; charset=utf-8").
			log().all();	
	}
	
}
