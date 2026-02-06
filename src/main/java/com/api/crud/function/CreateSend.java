package com.api.crud.function;

import java.io.File;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateSend {
	
	public void post() {
		File file = new File("src/main/resources/Complex.json");
		RequestSpecification rs = RestAssured.given();
		rs.body(file);
		
		Response resPost = rs.post("https://httpbin.org/post");
		
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resPost.statusCode() == 200); // int/double equal == & String : equals()
		sf.assertTrue(resPost.time() < 2000);
		sf.assertTrue(resPost.contentType().contains("json"));
		sf.assertTrue(!resPost.body().asString().equals(null));
		
		sf.assertTrue(resPost.body().asString().contains("name"));
		sf.assertTrue(resPost.body().asString().contains("salary"));
		
		// value = json parser = JsonPath
		JsonPath jp = resPost.jsonPath();
		sf.assertTrue(jp.get("json.name").equals("Sarower"));
		sf.assertTrue(jp.get("json.salary").equals(8000));
		
		Map<String, Object> myMap = jp.getMap("json");
		System.out.println(myMap);
		myMap.forEach((key, value) -> {
			System.out.println(key + " = " + value);
		});
		
		sf.assertAll();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateSend obj = new CreateSend();
		obj.post();

	}

}
