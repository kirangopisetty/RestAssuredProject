package com.api.tests;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TC08_UPDATE_USER_PATCH_API_EXTERNAL_JSON_FILE {
	
	@Test
	public void updateUserPATCHapiExternalJSONFile() throws FileNotFoundException {
		
		File file = new File(".\\src\\test\\resources\\requestBodyPATCH.json");	// opening the specified file
		FileReader filereader = new FileReader(file);	// reading the opened file contents
		JSONTokener jsonTokener = new JSONTokener(filereader);	// converting byte[] streams into human readable format
		JSONObject requestBody = new JSONObject(jsonTokener);	// convert json data into key-value pair format
				
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
			.when()
				.body(requestBody.toString())
				.patch("https://gorest.co.in/public/v2/users/7745087")
				
			.then()
				.log().all()
				.statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.time(greaterThan(100L))
				.time(lessThan(3000L))
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.body("name", equalTo("Mr.Rama"))
				.body("gender", equalTo("male"))
				.body("email", equalTo("rama@laxman.com"))
				.body("status", equalTo("active"));	
		}
}