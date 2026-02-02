package com.api.crud.function;

// import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadData {
	
	public void get() {
		// request
				// RestAssured.get("https://httpbin.org/get");
				
				// response
				// Response resObj = RestAssured.get("https://httpbin.org/get");
				
				// test cases and validation
				/*
				 * SoftAssert sf = new SoftAssert(); sf.assertTrue(resObj.statusCode() == 200);
				 * sf.assertEquals(resObj.statusCode(), 200); sf.assertTrue(resObj.time() <
				 * 2000); sf.assertTrue(resObj.contentType().contains("json"));
				 * sf.assertTrue(!resObj.body().equals(null));
				 * sf.assertTrue(resObj.body().asString().contains("Host"));
				 */
				
		// get data or get response
		Response resObj = RestAssured.get("https://httpbin.org/get");
		resObj.print();
		
		// common tests
		System.out.println(resObj.statusCode());
		// soft assertion
				SoftAssert soft = new SoftAssert();
				// soft.assertTrue(condition);
				soft.assertEquals(resObj.statusCode(), 200);
		// System.out.println(res.time());
		soft.assertTrue(resObj.time() < 2000);
		// System.out.println(res.contentType());
		soft.assertTrue(resObj.contentType().contains("json"));
		// System.out.println(!res.body().equals(null));
		soft.assertTrue(!resObj.body().equals(null));
		
		// based on requirements
		// System.out.println(res.body().asString().contains("Host"));
		soft.assertTrue(resObj.body().asString().contains("Host"));
		
		// values in Postman
		// var jsonData = pm.response.json(); // JSON parser = break JSON
		// pm.expect(jsonData.headers.Host).to.eql("httpbin.org");
		
		// Java JSON Parser >> JSON Path
		JsonPath jp = resObj.jsonPath(); // JSON parser
		boolean status = jp.get("headers.Host").toString().contains("httpbin.org");
		// System.out.println(status);
		// sf.assertTrue(jp.get("headers.Host").equals("httpbin.org"));
		soft.assertTrue(status);
		
		// validation via assertion
		// .assertTrue(condition);
		// Assert.assertEquals("", ""); // hard assert stops all test runs just because one test fails
		soft.assertAll();		
		// sf.assertAll();
	}

	public static void main(String[] args) {
		ReadData obj = new ReadData();
		obj.get();	
	}
	
}
