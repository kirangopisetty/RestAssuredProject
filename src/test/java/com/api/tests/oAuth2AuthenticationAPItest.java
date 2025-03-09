package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;

public class oAuth2AuthenticationAPItest {
	
	@Test
	public void oAuth2API() {
		
		given()
			.header("Accept", "application/json")
			.auth().oauth2("enter your OAuth2 token here")
			
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();			
	}
}