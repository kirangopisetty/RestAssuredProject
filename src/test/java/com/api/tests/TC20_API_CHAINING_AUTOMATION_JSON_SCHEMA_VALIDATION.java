package com.api.tests;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class TC20_API_CHAINING_AUTOMATION_JSON_SCHEMA_VALIDATION {

	int extractedID;
	@Test (priority = 1)
	public void createUserPOSTapiJavaFakerLibrary() {
		
		Faker faker = new Faker();	
			
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status","active");
		
		extractedID = given()
				.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 Edg/131.0.2903.86")
				.header("Accept","application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody)
				.post("https://gorest.co.in/public/v2/users")
				.jsonPath().getInt("id");	// to extract ID from the POST API response body
				System.out.println("The user is created with ID >> "+extractedID);
				
		/*	.then()
				.log().status()
				.log().body()
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created")
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.time(Matchers.lessThan(4000L))
				.body("status", equalTo("active"))
				.header("Content-Type", "application/json; charset=utf-8");	*/
		}	
	
	@Test (priority = 2)
	public void updateUserPATCHapiHashMap() {
		
		Faker faker = new Faker();	
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().fullName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "inactive");
		
		
			given()
			.header("User-Agent","Mozilla/5.0 (X11; Linux i686; rv:136.0) Gecko/20100101 Firefox/136.0")
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody)
				.patch("https://gorest.co.in/public/v2/users/"+extractedID)
			
			.then()
				.log().body()
				.statusCode(200)
				.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaPATCHapi.json"))
				.header("Content-Type", "application/json; charset=utf-8")
				.time(lessThan(3000L))
				.time(greaterThan(100L))
				.time(lessThan(3000L)).and().time(greaterThan(100L))
				.time(lessThanOrEqualTo(3000L)).and().time(greaterThanOrEqualTo(100L))
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.body("status", equalTo("inactive"));
				System.out.println("The user is updated with ID >> "+extractedID);
		}
	
	@Test (priority = 3)
	public void deleteUserAPI() {
		
		given()
		.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 14_7_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.3 Safari/605.1.15")
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().status()
			.time(lessThanOrEqualTo(3000L))
			.time(greaterThanOrEqualTo(100L))
			.statusCode(204)
			.statusLine("HTTP/1.1 204 No Content")
			.body(isEmptyOrNullString());	
			System.out.println("The user is deleted with ID >> "+extractedID);
	}
}