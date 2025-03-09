package com.api.tests;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.file.Files;
import io.restassured.response.Response;

public class fileDownloadAPI {

	@Test
	public void fileDownload() throws IOException {
		
	Response response=	given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
		.when()
			.get("https://gorest.co.in/public/v2/users")
			.thenReturn();
			//.andReturn();
		System.out.println("Response code >> "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	
		byte[] bytes = response.getBody().asByteArray();
		File file = new File("C:\\Users\\Kiran\\Downloads\\REST ASSURED TESTING\\allUsersResponse1.json");
		Files.write(file.toPath(), bytes);
	}
}