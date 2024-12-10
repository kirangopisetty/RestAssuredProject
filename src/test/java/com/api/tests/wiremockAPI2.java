package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class wiremockAPI2 {
	
	@Test
	public void getFileContent() {
		
		given()
		
		.when()
			.get("http://localhost:8080/viewFileContent")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml")
			.log().all();
	}
}