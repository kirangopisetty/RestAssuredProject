package com.api.tests;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class TC16_CREATE_USER_POST_API_HASHMAP_ASSERT_METHODS {
		
	@Test
	public void createUserPOSTapiHashMapAssertFunctions() {
		
		Faker faker = new Faker();	
			
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status","active");
		
		Response response = given()
				.header("Accept","application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody)
				.post("https://gorest.co.in/public/v2/users");
			
			// Capturing response attributes
			System.out.println("Status Code >> "+response.getStatusCode());
			System.out.println("Status Line >> "+response.getStatusLine());
			System.out.println("Response time >> "+response.getTime()+" ms");
			System.out.println("Response Headers >> "+response.getHeaders());
			System.out.println("Content-Length >> "+response.getHeader("Content-Length"));
			System.out.println("Response Body >> "+response.getBody().asString());
			System.out.println("Response Body >> "+response.getBody().asPrettyString());
			
			// Assertions in when() block
			
			Assert.assertEquals(response.getStatusCode(), 201);	// verify if statusCode=201
			Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");	// verify if the statusLine=HTTP/1.1 201 Created
			Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");	// verify if response body is in JSON format
			
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
}