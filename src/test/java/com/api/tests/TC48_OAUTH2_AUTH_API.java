package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;

public class TC48_OAUTH2_AUTH_API {
	
	@Test
	public void oAuth2API() {
		
		given()
			.header("Accept", "application/json")
			.auth().oauth2("enter your OAuth2 token here")
			
		.when()
			.get("https://api.github.com/user/repos")
			
			// OAuth2 GET/POST/PUT/DELETE APIs are available at below:
			// https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#create-a-repository-for-the-authenticated-user
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();			
	}
}