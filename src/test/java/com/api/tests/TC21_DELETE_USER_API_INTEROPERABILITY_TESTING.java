package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class TC21_DELETE_USER_API_INTEROPERABILITY_TESTING {
	
	@Test
	public void deleteUserAPI() {
		
		given()
			.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 Edg/131.0.2903.86")
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/7753851")
		
		.then()
			.log().status()
			.statusCode(204)
			.statusLine("HTTP/1.1 204 No Content")
			.body(isEmptyOrNullString());	
	}
}