package com.api.crud.function;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleWayPost {
	
	
	public Response getPostSetup() {
		File jsonData = new File("./src/main/resources/ComplexUpdate.json");
		RequestSpecification rs = RestAssured.given();
		rs.body(jsonData);
		rs.header("Content-Type", "application/json");
		
		Response resPost = rs.post("https://httpbin.org/post");
		// resPost.print();
		
		return resPost;
	}
	
	public void basicValidation(Response resPost) {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resPost.statusCode() == 200);
		sa.assertTrue(resPost.time() < 4000);
		sa.assertTrue(resPost.contentType().contains("json"));
		boolean nullStatus = !resPost.body().equals(null);
		sa.assertTrue(nullStatus);
		sa.assertAll();
	}
	
	public void getKeyValidation(Response resPost, String key) {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resPost.body().asString().contains(key));
	}
	
	public void arrayMapData(Response resPost, String jsonKey, int index, String mapKey, String tableValue) {
		JsonPath jp = resPost.jsonPath();

		// Array + Map Value ==> projects
		List<Map<String, Object>> projects = jp.getList(jsonKey);
		
		Map<String, Object> eachProject = projects.get(index); // index
		
		String value = eachProject.get(mapKey).toString();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(value.contains(tableValue));

	}
	
	public void jsonMapData(Response resPost, String jsonKey, String mapKey, String validValue) {
		JsonPath jp = resPost.jsonPath();

		// Map Value
		Map<String, Object> data = jp.getMap(jsonKey);

		SoftAssert sa = new SoftAssert();
		sa.assertTrue(data.get(mapKey).toString().contains(validValue));
	}
	
	public void getSimpleValue(Response resPost, String jsonKey, String validValue) {
		JsonPath jp = resPost.jsonPath();
		String keyValue = jp.get(jsonKey).toString();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(keyValue.contains(validValue));
	}

	public void jsonDataArray(Response resPost, String jsonKey, int index, String validValue) {
		JsonPath jp = resPost.jsonPath();

		// Array Value
		List<String> skills = jp.getList(jsonKey);
		String keyvalue = skills.get(index).toString();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(keyvalue.contains(validValue));

	}
}
