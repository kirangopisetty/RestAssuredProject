package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import org.json.JSONObject;

public class TC40_CREATE_USER_POST_API_DDT_XLSX_APPROACH4 {
	
	@DataProvider(name="createUserDDT")
	public Object[][] DDT() throws IOException {
		
		String xlfile = "C:\\Users\\Kiran\\Downloads\\REST ASSURED-API AUTOMATION TESTING\\createUserTestData.xlsx";
		String xlsheet = "Sheet1";
		int rowNum = TC39_XLUTILS.getRowsCount(xlfile, xlsheet);
		int colCount = TC39_XLUTILS.getColumnsCount(xlfile, xlsheet);
		
		String empdata[][]=new String[rowNum][colCount];
		for (int i=1;i<=rowNum;i++) {
			for (int j=0;j<colCount;j++) {
				empdata[i-1][j] = TC39_XLUTILS.getCellData(xlfile, xlsheet,i,j);
			}
		}
		return empdata;
	}
	
	@Test(dataProvider="createUserDDT")
	public void createUserPOSTapiDDT(String name, String gender, String email, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("gender", gender);
		requestBody.put("email", email);
		requestBody.put("status", status);
			
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody.toString())
				.post("https://gorest.co.in/public/v2/users")
			
			.then()
				.log().status()
				.log().body()
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created")
				.time(lessThan(4000L))
				.header("Content-Type", "application/json; charset=utf-8");
		}
}