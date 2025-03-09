package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class TC20_UpdateUserPATCHapiHashMapInteroperabilityTesting {
	
	@Test
	public void updateUserPATCHapiHashMap() {
		
	Faker faker = new Faker();	
	HashMap<String, String> requestBody = new HashMap<String, String>();
	requestBody.put("name", faker.name().prefix()+" "+faker.name().lastName());
	requestBody.put("email", faker.internet().emailAddress());
	requestBody.put("status", "inactive");
	
	
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 14_7_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.3 Safari/605.1.15")

		.when()
			.body(requestBody)
			.patch("https://gorest.co.in/public/v2/users/7745087")
		
		.then()
			.log().body()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaPATCHapi.json"))
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.time(lessThan(3000L))
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.body("status", equalTo("inactive"));
	}
}