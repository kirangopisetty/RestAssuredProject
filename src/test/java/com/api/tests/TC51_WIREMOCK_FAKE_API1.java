package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class TC51_WIREMOCK_FAKE_API1 {
	
	@Test
	public void getAllCourses() {
		
		given()
		
		.when()
			.get("http://localhost:8080/allCourses")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.log().all();
	}
}