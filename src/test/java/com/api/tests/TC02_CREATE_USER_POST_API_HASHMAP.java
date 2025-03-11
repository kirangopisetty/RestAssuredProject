package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;

public class TC02_CREATE_USER_POST_API_HASHMAP {
	
	@Test
	public void createUserAPI() {
		
	HashMap <String, String> requestBody = new HashMap<String, String>();	// an object requestBody is instantiated for the class HashMap
	requestBody.put("name", "Kiran");	// creating a variable called name & assigning a value Kiran to it
	requestBody.put("gender", "male");
	requestBody.put("email", "reslz@asured.com");
	requestBody.put("status", "active");
	
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
		//	.contentType("ContentType.JSON")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
		.when()
			.body(requestBody)
			.post("https://gorest.co.in/public/v2/users")
		
		.then()
			.log().status()
			.log().body()
			.statusCode(201)
			.statusLine("HTTP/1.1 201 Created")
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.time(Matchers.lessThan(4000L))
			.time(Matchers.greaterThan(100L))
		//  .time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)));
			.body("name", equalTo("Kiran"))
			.body("gender", equalTo("male"))
			.body("status", equalTo("active"))
			.header("Content-Type", "application/json; charset=utf-8");	
	}
}