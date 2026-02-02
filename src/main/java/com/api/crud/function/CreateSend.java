package com.api.crud.function;

import java.io.File;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateSend {
	
	public void post() {
		File file = new File("src/main/resources/Simple.json");
		RequestSpecification rs = RestAssured.given();
		rs.body(file);
		
		Response resPost = rs.post("https://httpbin.org/post");
		
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resPost.statusCode() == 200); // int/double equal == & String : equals()
		sf.assertTrue(resPost.time() < 2000);
		sf.assertTrue(resPost.contentType().contains("json"));
		sf.assertTrue(!resPost.body().asString().equals(null));
		
		sf.assertTrue(resPost.body().asString().contains("Name"));
		sf.assertTrue(resPost.body().asString().contains("Salary"));
		
		// value = json parser = JsonPath
		JsonPath jp = resPost.jsonPath();
		sf.assertTrue(jp.get("json.Name").equals("Sarower"));
		sf.assertTrue(jp.get("json.Salary").equals(5000));
		sf.assertAll();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateSend obj = new CreateSend();
		obj.post();

	}

}
