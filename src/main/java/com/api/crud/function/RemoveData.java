package com.api.crud.function;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RemoveData {
	
	public void delete() {
		Response resDelete = RestAssured.delete("https://httpbin.org/delete");
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resDelete.statusCode() == 200);
		sf.assertEquals(resDelete.statusCode(), 200);
		sf.assertTrue(resDelete.time() < 2000);
		sf.assertTrue(resDelete.contentType().contains("json"));
		sf.assertTrue(!resDelete.body().equals(null));
		sf.assertTrue(resDelete.body().asString().contains("json"));
		JsonPath jp = resDelete.jsonPath();
		try {
			sf.assertTrue(jp.get("json").toString().equals("null"));
		} catch (Exception e) {
			
		}
		// sf.assertEquals(jp.get("json").toString(), null);
		sf.assertAll();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveData obj = new RemoveData();
		obj.delete();
	}

}
