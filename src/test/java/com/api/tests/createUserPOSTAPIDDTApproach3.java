package com.api.tests;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class createUserPOSTAPIDDTApproach3 extends testDataForDDT {
	
	@Test (dataProvider = "createUserDDT")
	public void createUserPostAPIdataDrivenAutomationTest(String name, String email, String gender, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("email", email);
		requestBody.put("gender", gender);
		requestBody.put("status", status);
		
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