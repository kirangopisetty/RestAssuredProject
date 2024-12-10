package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class oAuth2AuthenticationAPItest {
	
	@Test
	public void oAuth2API() {
		
		given()
			.header("Accept", "application/json")
			.auth().oauth2("type_your_OAuth2_token_here")
			
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();			
	}
}