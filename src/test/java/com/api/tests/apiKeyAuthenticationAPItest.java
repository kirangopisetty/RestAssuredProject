package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class apiKeyAuthenticationAPItest {
	
	
	@Test
	public void apiKeyAuthAPI1() {
		
		given()
			.pathParam("myPath", "/geo/1.0/direct")
			.queryParam("q", "Chennai")
			.queryParam("appid", "ed9b8ed0edc090919795def6f872aef8")
			
		.when()
			.get("http://api.openweathermap.org/{myPath}")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();			
	}
	
	@Test
	public void apiKeyAuthAPI2() {
		
		given()
			
		.when()
			.get("http://api.openweathermap.org/geo/1.0/direct?q=Hyderabad&appid=ed9b8ed0edc090919795def6f872aef8")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();			
	}
}