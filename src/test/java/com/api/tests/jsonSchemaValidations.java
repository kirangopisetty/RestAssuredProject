package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class jsonSchemaValidations {
	
	Faker faker = new Faker();
	
	@Test
	public void createUserPostAPIjsonObject() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "active");
		
		given().
			header("Accept", "application/json").
			header("Content-Type", "application/json").
			header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
		
		when().
			body(requestBody.toString()).
			post("https://gorest.co.in/public/v2/users").
		
		then().
			assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("postAPIschema.json")).
			statusCode(201).
			header("Content-Type", "application/json; charset=utf-8").
			log().all();
	}
	
	@Test
	public void updateUserAPI() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().lastName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "active");
			
			given().
				header("Accept", "application/json").
				header("Content-Type", "application/json").
				header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
					
			when().
				body(requestBody.toString()).
				patch("https://gorest.co.in/public/v2/users/7569633").
			
			then().
				assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("patchAPIschema.json")).
				statusCode(200).
				header("Content-Type", "application/json; charset=utf-8").
				log().all();	
		}
	
	@Test
	public void listUsersAPI() {
		
	given().
		header("Accept", "application/json").
		header("Content-Type", "application/json").
		header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29");
	
	when().
		get("https://gorest.co.in/public/v2/users").
	
	then().
		assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAPIschema.json")).
		statusCode(200).
		log().all();	// prints response body + response headers
	}
	
}
