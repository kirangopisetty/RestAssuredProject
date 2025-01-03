package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.json.JSONObject;

public class createUserPOSTAPIjsonObject {

	@Test
	public void createUserPostAPIjsonObject() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Postman");	// creating a variable called name & assigning a value Postman to it
		requestBody.put("gender", "male");
		requestBody.put("email", "posbt@assured.com");
		requestBody.put("status", "inactive");
		
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody.toString())
			.post("https://gorest.co.in/public/v2/users")
		
		.then()
			.statusCode(201)
			.statusLine("HTTP/1.1 201 Created")
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.log().status()
			.time(Matchers.lessThan(2000L))
			//.time(Matchers.greaterThan(1000L))
			//.time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)));
			.body("name", equalTo("Postman"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
}