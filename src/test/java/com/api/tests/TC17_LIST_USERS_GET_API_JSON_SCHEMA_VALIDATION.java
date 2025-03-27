package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;

public class TC17_LIST_USERS_GET_API_JSON_SCHEMA_VALIDATION {

	@Test
	public void listUsersGETapi() {
		
		given()	// api request section
			.header("Accept", "application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()		// api request section
			.get("https://gorest.co.in/public/v2/users")
			
		.then()		// api response section
			.log().body()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaGETapi.json"))
			.body("[1].id", greaterThan(0))
			.time(Matchers.lessThan(2000L))	//Verify if response time < 2 seconds
			.statusCode(200)	// Verify if response code=200 OK
			.statusLine("HTTP/1.1 200 OK")	// Verify if status line = 200 OK
			.header("Content-Type", "application/json; charset=utf-8")	// Verify if response body is in JSON format
			.body("gender", hasItems("male","female"))		// Verify if gender is either male or female
			.body("status", hasItems("active","inactive"));	// Verify if status is either active or inactive
	}
}