package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

public class updateUserPATCHAPIHashMap {
	
	@Test
	public void updateUserAPI() {
		
	HashMap<String, String> requestBody = new HashMap<String, String>();
	requestBody.put("name", "HashMapRestAssured");
	requestBody.put("email", "hashmap@patch.com");
	requestBody.put("status", "inactive");
		
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
				
		.when()
			.body(requestBody)
			.patch("https://gorest.co.in/public/v2/users/7563934")
		
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
