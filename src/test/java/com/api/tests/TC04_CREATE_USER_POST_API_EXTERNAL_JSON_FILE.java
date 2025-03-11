package com.api.tests;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONTokener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TC04_CREATE_USER_POST_API_EXTERNAL_JSON_FILE {
	
	@Test
	public void createUserPOSTapiExternalJSONfile() throws FileNotFoundException {
		
		File file = new File(".\\src\\test\\resources\\requestBodyPOST.json");	// opening the specified file
		FileReader filereader = new FileReader(file);	// reading the opened file contents
		JSONTokener jsonTokener = new JSONTokener(filereader);	// converting byte[] streams into human readable format
		JSONObject requestBody = new JSONObject(jsonTokener);	// convert json data into key-value pair format
		
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody.toString())
				.post("https://gorest.co.in/public/v2/users ")
			
			.then()
				.log().all()
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created")
				.time(lessThan(5000L))
				.header("Content-Type", "application/json; charset=utf-8")
				.body("name", equalTo("Ram"))
				.body("gender", equalTo("male"))
				.body("status", equalTo("active"));
		}
}