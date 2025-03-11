package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.json.JSONObject;

public class TC03_CREATE_USER_POST_API_JSONOBJECT {

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
			.log().status()
			.log().body()
			.log().headers()
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.statusCode(201)
			.statusLine("HTTP/1.1 201 Created")
			.time(lessThan(4000L))
		//	.time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)));
			.header("Content-Type", "application/json; charset=utf-8")
			.body("name", equalTo("Isha"))
			.body("gender", equalTo("female"))
			.body("status", equalTo("active"));
	}
}