package com.api.tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import java.util.HashMap;

public class createUserPostAPIHashMap {
	
	@Test
	public void createUserAPI() {
		
	HashMap <String, String> requestBody = new HashMap<String, String>();	// an object requestBody is instantiated for the class HashMap
	requestBody.put("name", "Kiran");	// creating a variable called name & assigning a value Kiran to it
	requestBody.put("gender", "male");
	requestBody.put("email", "reslz@assured.com");
	requestBody.put("status", "active");
	
		given().
			header("Accept", "application/json").
			header("Content-Type", "application/json").
		//	contentType("ContentType.JSON").
			header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
			
		when().
			body(requestBody).
			post("https://gorest.co.in/public/v2/users").
		
		then().
			statusCode(201).
			header("Content-Type", "application/json; charset=utf-8").
			log().all();
	}
}