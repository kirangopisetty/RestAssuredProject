package com.api.tests;

import static org.hamcrest.Matchers.oneOf;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class createUserPOSTAPIjavaFakerLibrary {
	
	Faker faker = new Faker();
	
	@Test
	public void createUserPostAPIjsonObject() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().firstName());	// creating a variable called name & assigning a value Postman to it
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "inactive");
		
	Response response = given().
			header("Accept", "application/json").
			header("Content-Type", "application/json").
			header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
		
		when().
			body(requestBody.toString()).
			post("https://gorest.co.in/public/v2/users");	
		
			Assert.assertEquals(response.getStatusCode(), 201);
			Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
			Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
			
			
			System.out.println("Status Code >> "+response.getStatusCode());
			System.out.println("Status Line >> "+response.getStatusLine());
			System.out.println("Response Time >> "+response.getTime()+" ms");
			System.out.println("Response Headers >> "+response.getHeaders());
			System.out.println("Response Body >> "+response.getBody());
							
		// then().
		//	statusCode(201).
		//	header("Content-Type", "application/json; charset=utf-8").
		//	log().all();		
	}
}