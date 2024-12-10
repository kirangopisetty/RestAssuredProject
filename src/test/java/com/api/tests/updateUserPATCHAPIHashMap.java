package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class updateUserPATCHAPIHashMap {
	
	@Test
	public void updateUserAPI() {
		
	HashMap<String, String> requestBody = new HashMap<String, String>();
	requestBody.put("name", "HashMapRestAssured");
	requestBody.put("email", "hashmap@patch.com");
	requestBody.put("status", "inactive");
		
		given().
			header("Accept", "application/json").
			header("Content-Type", "application/json").
			header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
				
		when().
			body(requestBody).
			patch("https://gorest.co.in/public/v2/users/7563934").
		
		then().
			statusCode(200).
			header("Content-Type", "application/json; charset=utf-8").
			log().all();	
	}
}
