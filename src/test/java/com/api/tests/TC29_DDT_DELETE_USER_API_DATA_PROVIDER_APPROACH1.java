package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class TC29_DDT_DELETE_USER_API_DATA_PROVIDER_APPROACH1 {
	
	@DataProvider(name="deleteUserDDT")
	public Object[] DDT() {
		Object[] requestBody = new Object[4];	// 4 rows, 1 column
		requestBody[0] = "7761133";
		requestBody[1] = "7761150";
		requestBody[2] = "7761149";
		requestBody[3] = "7761148";
		
		return requestBody;
	}

	@Test(dataProvider="deleteUserDDT")
	public void deleteUserAPI(Object idToDelete) {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/"+idToDelete)
		
		.then()
			.log().status()
			.statusCode(204)
			.statusLine("HTTP/1.1 204 No Content")
			.body(isEmptyOrNullString());
			System.out.println("The ID deleted is >> "+idToDelete);
	}
}