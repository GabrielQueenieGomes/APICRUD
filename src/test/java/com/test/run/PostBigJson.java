package com.test.run;

import java.io.File;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostBigJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RequestSpecification rqs = RestAssured.given();
		
		// Test data in body
		File jsonFile = new File("./src/main/resources/ComplexUpdate.json");
		rqs.body(jsonFile);
		rqs.header("Content-Type", "application/json");
		Response responseObj = rqs.post("https://httpbin.org/post"); // send
		responseObj.prettyPrint();
		System.out.println("=========== Key =============");
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(responseObj.body().asString().contains("employeeId"));
		sa.assertTrue(responseObj.body().asString().contains("name"));
		sa.assertTrue(responseObj.body().asString().contains("skills"));
		sa.assertTrue(responseObj.body().asString().contains("projects"));
		
	//	=================== value ===========================
		
		JsonPath jp = responseObj.jsonPath(); // Json parser = JsonPath
		
		// ==================== simple/single value ================
		sa.assertTrue(jp.get("json.employeeId").equals("EMP001"));
		sa.assertTrue(jp.get("json.name").equals("David"));
		sa.assertTrue(jp.get("json.salary").equals(8000));
		
		System.out.println("============ Simple array ============");
		sa.assertTrue(jp.get("json.skills").toString().contains("Java"));
		sa.assertTrue(jp.get("json.skills").toString().contains("Selenium"));
		sa.assertTrue(jp.get("json.skills").toString().contains("RestAssured"));
		
		System.out.println("============ Complex array ============");

		sa.assertTrue(jp.get("json.projects.projectId").toString().contains("P101"));
		sa.assertTrue(jp.get("json.projects.projectName").toString().contains("Banking App"));
		
		Map<String, Object> myMap = jp.getMap("json");
		System.out.println(myMap);
		myMap.forEach((key, value) -> {
			System.out.println(key + " = " + value);
		});
		
		sa.assertAll();

	}

}
