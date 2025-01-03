package com.api.tests;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class updateUserPATCHAPIexternalJSONfile {
	
	@Test
	public void updateUserPatchAPIexternalJsonFile() throws FileNotFoundException {
		
		File file = new File(".\\src\\test\\resources\\payload.json");	// to locate the file
		FileReader fr = new FileReader(file);		// to open & read the file contents
		JSONTokener jt = new JSONTokener(fr);		// to tokenize & read the file contents correctly
		JSONObject requestBody = new JSONObject(jt);

		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
	
		.when()
			.body(requestBody.toString())
			.patch("https://gorest.co.in/public/v2/users/7564110")
		
		.then()
			.statusCode(200)
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.time(Matchers.lessThan(2000L))
			//.time(Matchers.greaterThan(1000L))
			//.time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)));
			.body("name", equalTo("Pushpa2Movie"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all()
			.log().status();	
	}
}