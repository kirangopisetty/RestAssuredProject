package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;

public class TC18_ListUsersGETapiInteroperabilityTesting {

	@Test
	public void listUsersGETapi() {
		
		given()	// api request section
			.header("Accept", "application/json")
			.header("Content-Type","application/json")
			.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()		// api request section
			.get("https://gorest.co.in/public/v2/users")
			
		.then()		// api response section
		//	.log().status()		// print status code
		//	.log().body()		// print response body
		//	.log().headers()	// print response headers
			.log().all()
			
			.body("[1].id", greaterThan(0))
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaGETapi.json"))
			.time(Matchers.lessThan(2000L))	//Verify if response time < 2 seconds
			.statusCode(200)	// Verify if response code=200 OK
			.statusLine("HTTP/1.1 200 OK")	// Verify if status line = 200 OK
			.header("Content-Type", "application/json; charset=utf-8")	// Verify if response body is in JSON format
			.body("gender", hasItems("male","female"))		// Verify if gender is either male or female
			.body("status", hasItems("active","inactive"));	// Verify if status is either active or inactive
		//	.body("[0].name", equalTo("Mina Mishra"))
		//	.body("[5].email", equalTo("tagore_kiran@conroy.test"));
	}
}