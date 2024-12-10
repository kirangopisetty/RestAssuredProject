package com.api.tests;

import org.testng.annotations.Test;
import org.json.JSONObject;
import com.github.javafaker.Faker;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import static io.restassured.RestAssured.*;

	@Epic ("API chaining EPIC")
	@Feature("Feature is to achieve api chaining using faker library")
	public class E2EapiTestAllureReports {
		
		int extractedID;
		Faker faker = new Faker();
		
		@Story("Create UserStory")
		@Step("POST Test Case")
		@Description("This API creates as random user")
		@Severity(SeverityLevel.BLOCKER)
		@Test (priority = 1)
		public void createUserAPI() {
			
			JSONObject requestBody = new JSONObject();
			requestBody.put("name", faker.name().firstName());
			requestBody.put("gender", faker.demographic().sex());
			requestBody.put("email", faker.internet().emailAddress());
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
		
		@Story("Update UserStory")
		@Step("PATCH Test Case")
		@Description("This API updates the created user")
		@Severity(SeverityLevel.CRITICAL)
		@Test (priority = 2, dependsOnMethods={"createUserAPI"})
		public void updateUserAPI() {
			
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("email", faker.internet().emailAddress());
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
		
		@Story("Delete UserStory")
		@Step("DELETE Test Case")
		@Description("This API deletes the created user")
		@Severity(SeverityLevel.NORMAL)
		@Test (priority = 3,dependsOnMethods={"createUserAPI"})
		public void deleteUserAPI() {
			
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
