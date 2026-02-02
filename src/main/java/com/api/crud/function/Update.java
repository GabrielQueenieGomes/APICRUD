package com.api.crud.function;

import java.io.File;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Update {
	
	public void updateData() {
		File file = new File("src/main/resources/Update.json");
		RequestSpecification rs = RestAssured.given();
		rs.body(file);
		
		Response resPut = rs.put("https://httpbin.org/put");
		
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resPut.statusCode() == 200); // int/double equal == & String : equals()
		sf.assertTrue(resPut.time() < 2000);
		sf.assertTrue(resPut.contentType().contains("json"));
		sf.assertTrue(!resPut.body().asString().equals(null));
		
		sf.assertTrue(resPut.body().asString().contains("Name"));
		sf.assertTrue(resPut.body().asString().contains("Salary"));
		
		// value = json parser = JsonPath
		JsonPath jp = resPut.jsonPath();
		sf.assertTrue(jp.get("json.Name").equals("David"));
		sf.assertTrue(jp.get("json.Salary").equals(15000));
		sf.assertAll();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Update obj = new Update();
		obj.updateData();

	}

}
