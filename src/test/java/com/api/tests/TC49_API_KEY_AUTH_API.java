package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class TC49_API_KEY_AUTH_API {
	
	@Test
	public void apiKeyAuthAPI1() {
		
		given()
			.pathParam("myPath", "/geo/1.0/direct")
			.queryParam("q", "Chennai")
			.queryParam("appid", "ed9b8ed0edc0909195def6f872aef8")
			
		.when()
			.get("http://api.openweathermap.org/{myPath}")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();			
	}
	
	@Test
	public void apiKeyAuthAPI2() {
		
		given()
			
		.when()
			.get("http://api.openweathermap.org/geo/1.0/direct?q=Hyderabad&appid=ed9b8ed0edc0909195def6f872aef8")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();			
	}
}