package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import java.util.HashMap;

public class TC18_CREATE_USER_POST_API_HASHMAP_JSON_SCHEMA_VALIDATION {
	
	@Test
	public void createUserPOSTapiJavaFakerLibrary() {
	
	Faker faker = new Faker();	
		
	HashMap<String, String> requestBody = new HashMap<String, String>();
	requestBody.put("name", faker.name().firstName());
	requestBody.put("gender", faker.demographic().sex());
	requestBody.put("email", faker.internet().emailAddress());
	requestBody.put("status","active");
	
		given()
			.header("Accept","application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.post("https://gorest.co.in/public/v2/users")
		
		.then()
			.log().status()
			.log().body()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaPOSTapi.json"))
			.statusCode(201)
			.statusLine("HTTP/1.1 201 Created")
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.time(Matchers.lessThan(4000L))
			.body("status", equalTo("active"))
			.header("Content-Type", "application/json; charset=utf-8");	
	}
}