package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class digestAuthenticationAPItest {
	
	@Test
	public void digestAuthAPI1() {
		
		given()
			.header("Accept", "application/json")
			.auth().digest("user", "passwd")
		
		.when()
			.get("https://httpbin.org/digest-auth/auth/user/passwd")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	public void digestAuthAPI2() {
		
		given()
			.header("Accept", "application/json")
			.auth().digest("user", "passwd")
		
		.when()
			.get("https://httpbin.org/digest-auth/auth/user/passwd/MD5")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	public void digestAuthAPI3() {
		
		given()
			.header("Accept", "application/json")
			.auth().digest("user", "passwd")
		
		.when()
			.get("https://httpbin.org/digest-auth/auth/user/passwd/MD5/never")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("authenticated", equalTo(true))
			.log().all();
	}
}