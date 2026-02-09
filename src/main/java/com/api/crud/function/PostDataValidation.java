package com.api.crud.function;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostDataValidation {
	
	Response resPost;
	
	public void getPost() {
		File jsonData = new File("./src/main/resources/ComplexUpdate.json");
		RequestSpecification rs = RestAssured.given();
		rs.body(jsonData);
		rs.header("Content-Type", "application/json");
		
		resPost = rs.post("https://httpbin.org/post");
		resPost.print();
	}

	public void basicValidation() {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resPost.statusCode() == 200);
		sa.assertTrue(resPost.time() < 200);
		sa.assertTrue(resPost.contentType().contains("json"));
		boolean nullStatus = !resPost.body().equals(null);
		sa.assertTrue(nullStatus);
	}
	
	public void getKeyValidation(String key) {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resPost.body().asString().contains(key));
	}
	
	public void getValueValidation() {
		JsonPath jp = resPost.jsonPath();
		
		// simple value
		String value = jp.get("JsonKey");
		
		// array value
		List<String> skills = jp.getList("JsonKey");
		
		// map value
		Map<String, Object> data = jp.getMap("JsonKey");
		
		// array map value
		List<Map<String, Object>> projects = jp.getList("JsonKey");
		
	}
	
	public void simpleSingleValue(String singleDataKey, String Value) {
		JsonPath jp = resPost.jsonPath();
		System.out.println(jp.get(singleDataKey).toString());
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(jp.get(singleDataKey).toString().contains(Value));
		sa.assertAll();
	}
	
	public void getArrayValue(String singleDataKey, String Value) {
		JsonPath jp = resPost.jsonPath();
		List<String> myArrayValue = jp.getList(singleDataKey);
		System.out.println(myArrayValue);
		SoftAssert sa = new SoftAssert();
		myArrayValue.forEach(v -> {
			System.out.println(v);
			if (v.contains(Value)) {
				sa.assertTrue(v.contains(Value));
			}
			
		});
		sa.assertAll();
	}
	
	public void getMapValue(String singleDataKey, String Value) {
		JsonPath jp = resPost.jsonPath();
		Map<String, Object> data = jp.getMap(singleDataKey);
		SoftAssert sa = new SoftAssert();
		data.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
			if (v.toString().contains(Value)) {
				sa.assertTrue(v.toString().contains(Value));
			}
			
		});
		sa.assertAll();
	}
	
	public void arrayMapValue(String singleDataKey, String Value) {
		JsonPath jp = resPost.jsonPath();
		List<Map<String, Object>> projects = jp.getList("json.projects");
		SoftAssert sa = new SoftAssert();
		
		/*
		 * Map<String, Object> firstProject = projects.get(0);
		 * sa.assertTrue(firstProject.get("projectName").equals("Banking App"));
		 * sa.assertTrue(firstProject.get("projectId").equals("101"));
		 * firstProject.forEach((k,v) -> { sa.assertTrue(k.equals("Banking App"));
		 * sa.assertTrue(v.equals("101")); });
		 */
		
		projects.forEach(project -> {
			project.forEach((k, v) -> {
				System.out.println(k);
				System.out.println(v);
				
				if(k.equals("Banking App") && v.equals("projectId")) {
					sa.assertTrue(k.equals("Banking App"));
					sa.assertTrue(v.equals("101"));
				}
				
				
			});
			
			
			/*
			 * if (v.contains(Value)) { sa.assertTrue(v.contains(Value)); }
			 */
			
			
		});
		sa.assertAll();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PostDataValidation pdv = new PostDataValidation();
		pdv.getPost();
		pdv.basicValidation();
		pdv.getKeyValidation("employeeId");
		pdv.getKeyValidation("name");
		pdv.getKeyValidation("projects");
		pdv.simpleSingleValue("json.name", "David");
		pdv.getArrayValue("json.skills", "Java");
		pdv.getMapValue("json.data", "John");
		pdv.arrayMapValue("json.projects", "Banking Map");

	}

}
