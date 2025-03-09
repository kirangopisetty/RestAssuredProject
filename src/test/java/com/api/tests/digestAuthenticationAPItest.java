package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;

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
			.time(Matchers.lessThan(2000L))
			.assertThat().body("authenticated", oneOf(true,false))
			.body("authenticated", equalTo(true))
			.body("user", equalTo("user"))
			.header("Content-Type", "application/json")
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
			.time(Matchers.lessThan(2000L))
			.assertThat().body("authenticated", oneOf(true,false))
			.body("authenticated", equalTo(true))
			.body("user", equalTo("user"))
			.header("Content-Type", "application/json")
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
			.time(Matchers.lessThan(2000L))
			.assertThat().body("authenticated", oneOf(true,false))
			.body("authenticated", equalTo(true))
			.body("user", equalTo("user"))
			.header("Content-Type", "application/json")
			.log().all();
	}
}