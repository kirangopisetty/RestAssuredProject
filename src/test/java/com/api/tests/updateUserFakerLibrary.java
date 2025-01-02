package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import org.json.JSONObject;
public class updateUserFakerLibrary {

	Faker faker = new Faker();
	@Test
	public void updateUserAPI() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().prefix()+" "+faker.name().lastName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "inactive");
		
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
				
		.when()
			.body(requestBody.toString())
			.patch("https://gorest.co.in/public/v2/users/7564009")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			//.time(Matchers.greaterThan(1000L))
			//.time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)));
			.body("name", equalTo("Pushpa2Movie"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all()
			.log().status();	
	}
}