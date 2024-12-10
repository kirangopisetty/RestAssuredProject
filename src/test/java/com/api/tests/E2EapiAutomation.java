package com.api.tests;

import static org.hamcrest.Matchers.hasItems;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

	public class E2EapiAutomation {
		
		int extractedID;
		Faker faker = new Faker();
		
		@Test (priority = 0)
		public void createUserAPI() {
			
			JSONObject requestBody = new JSONObject();
			requestBody.put("name", faker.name().firstName());	// creating a variable called name & assigning a value Postman to it
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
		//		assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("postAPIschema.json")).
		//		statusCode(201).
		//		header("Content-Type", "application/json; charset=utf-8").
		//		log().all();
}
		@Test (priority = 1, dependsOnMethods={"createUserAPI"})
		public void verifyIfUserIsCreated() {
			
			given().
				header("Accept", "application/json").
				header("Content-Type", "application/json").
				header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29");
				
			when().
				get("https://gorest.co.in/public/v2/users").
			
			then().
				assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAPIschema.json")).
				statusCode(200).
				body("gender", hasItems("male", "female")).
				body("status", hasItems("active", "inactive")).
			//	body("id", hasItems(+extractedID)).
			//	body("[0].name", equalTo("Nawal Bhattathiri")).
			//	body("[1].id", equalTo(7561792)).
			//	body("[2].email", equalTo("shwet_tandon@reichel-ledner.test")).
				log().body();	// prints only response body
			}
		
		@Test (priority = 2, dependsOnMethods={"createUserAPI"})
		public void updateUserAPI() {
			
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().prefix()+" "+faker.name().firstName());
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
				assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("patchAPIschema.json")).
				statusCode(200).
				header("Content-Type", "application/json; charset=utf-8").
				log().all();	
		}
		
		@Test (priority = 3, dependsOnMethods={"createUserAPI"})
		public void verifyIfUserIsUpdated() {
			
			given().
				header("Accept", "application/json").
				header("Content-Type", "application/json").
				header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29");
			
			when().
				get("https://gorest.co.in/public/v2/users").
			
			then().
				assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAPIschema.json")).
				statusCode(200).
				body("gender", hasItems("male", "female")).
				body("status", hasItems("active", "inactive")).
			//	body("id", hasItems(+extractedID)).
			//	body("[0].name", equalTo("Nawal Bhattathiri")).
			//	body("[1].id", equalTo(7561792)).
			//	body("[2].email", equalTo("shwet_tandon@reichel-ledner.test")).
				log().body();	// prints only response body
			}
		
		@Test (priority = 4,dependsOnMethods={"createUserAPI"})
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
		
		@Test (priority = 5, dependsOnMethods={"createUserAPI"})
		public void verifyIfUserIsDeleted() {
			
			given().
				header("Accept", "application/json").
				header("Content-Type", "application/json").
				header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29");
			
			when().
				get("https://gorest.co.in/public/v2/users").
			
			then().
				assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAPIschema.json")).
				statusCode(200).
				body("gender", hasItems("male", "female")).
				body("status", hasItems("active", "inactive")).
			//	body("[0].name", equalTo("Nawal Bhattathiri")).
			//	body("[1].id", equalTo(7561792)).
			//	body("[2].email", equalTo("shwet_tandon@reichel-ledner.test")).
				log().body();	// prints only response body
			}
}
