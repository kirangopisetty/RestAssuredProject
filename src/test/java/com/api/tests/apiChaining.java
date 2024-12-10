package com.api.tests;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

	public class apiChaining {
		
		int extractedID;
		
		@Test (priority = 1)
		public void createUserAPI() {
			
			JSONObject requestBody = new JSONObject();
			requestBody.put("name", "User2");	// creating a variable called name & assigning a value Postman to it
			requestBody.put("gender", "male");
			requestBody.put("email", "user2@restassured.com");
			requestBody.put("status", "active");
			
			extractedID = given().
				header("Accept", "application/json").
				header("Content-Type", "application/json").
				header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
			
			when().
				body(requestBody.toString()).
				post("https://gorest.co.in/public/v2/users").
				jsonPath().getInt("id");
			
		//	then().
		//		statusCode(201).
		//		header("Content-Type", "application/json; charset=utf-8").
		//		log().all();
}
		
		@Test (priority = 2, dependsOnMethods={"createUserAPI"})
		public void updateUserAPI() {
			
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "USERTWO");
		requestBody.put("email", "usertwo2@restassured.com");
		requestBody.put("status", "inactive");
			
			given().
				header("Accept", "application/json").
				header("Content-Type", "application/json").
				header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
					
			when().
				body(requestBody.toString()).
				patch("https://gorest.co.in/public/v2/users/"+extractedID).
			
			then().
				statusCode(200).
				header("Content-Type", "application/json; charset=utf-8").
				log().all();	
		}
		
		@Test (priority = 3,dependsOnMethods={"createUserAPI"})
		public void deleteUser() {
			
			given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")

			.when()
				.delete("https://gorest.co.in/public/v2/users/"+extractedID)
			
			.then()
				.statusCode(204);
		}
}
