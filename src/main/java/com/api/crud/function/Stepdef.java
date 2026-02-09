package com.api.crud.function;

// import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Stepdef {
	
	Response resPost;
	
	@Given("set up post url & find json file")
	public void set_up_post_url_find_json_file() {
		File jsonData = new File("./src/main/resources/ComplexUpdate.json");
		String baseUrl = "https://httpbin.org/post";
	    
	}

	@When("send request and get response for post")
	public void send_request_and_get_response_for_post() {
		SimpleWayPost swp = new SimpleWayPost();
		resPost = swp.getPostSetup();
	}
	
	@Then("validate api basic validation")
	public void validate_api_basic_validation() {
		SimpleWayPost swp = new SimpleWayPost();
		swp.basicValidation(resPost);
	}

	@Then("validate json key {string}")
	public void validate_json_key(String key) {
		System.out.println("JSON Key = " + key);
		SimpleWayPost swp = new SimpleWayPost();
		swp.getKeyValidation(resPost, key);
	}

	@Then("validate json value {string}")
	public void validate_json_value(String tableValue) {
	    System.out.println("Feature file value = " + tableValue);
	    SimpleWayPost swp = new SimpleWayPost();
	    
	    if (tableValue.contains("EMP001") || tableValue.contains("David") || tableValue.contains("8000")) {
			if (tableValue.contains("EMP001")) {
				swp.getSimpleValue(resPost, "json.employeeId", tableValue);
			}
			if (tableValue.contains("David")) {
				swp.getSimpleValue(resPost, "json.name", tableValue);
			}
			if (tableValue.contains("8000")) {
				swp.getSimpleValue(resPost, "json.salary", tableValue);
			}

		}
	    
	    if (tableValue.contains("Selenium")) { // array
	    	swp.jsonDataArray(resPost, "json.skills", 1, tableValue);
		}
	    
	    // Map
		if (tableValue.contains("John")) { // Map
			swp.jsonMapData(resPost, "json.data", "name", tableValue);
			
		}
	
		if (tableValue.contains("Banking App") || tableValue.contains("101")) { // Array + Map
			if(tableValue.contains("Banking App")) {
				swp.arrayMapData(resPost, "json.projects", 0, "projectName", tableValue);
				
			}
			
			if(tableValue.contains("101")) {
				swp.arrayMapData(resPost, "json.projects", 0, "projectId", tableValue);
			}
			
		}
	}
	
	/*
	 * String url; String urlPost; String urlPut; String urlTrash; Response resObj;
	 * Response resPost; Response resPut; Response resDelete;
	 * 
	 * @Given("put url") public void put_url() { url = "https://httpbin.org/get";
	 * 
	 * }
	 * 
	 * @When("send get request and response") public void
	 * send_get_request_and_response() { resObj = RestAssured.get(url);
	 * 
	 * // response.print(); }
	 * 
	 * @Then("validate data with test cases") public void
	 * validate_data_with_test_cases() { SoftAssert sf = new SoftAssert();
	 * sf.assertTrue(resObj.statusCode() == 200);// int/double equal == & String :
	 * equals() sf.assertTrue(resObj.time() < 2000);
	 * sf.assertTrue(resObj.contentType().contains("json"));
	 * sf.assertTrue(!resObj.body().asString().equals(null));
	 * 
	 * sf.assertTrue(resObj.body().asString().contains("Host"));
	 * 
	 * // value = json parser = JsonPath JsonPath jp = resObj.jsonPath();
	 * sf.assertTrue(jp.get("headers.Host").equals("httpbin.org")); sf.assertAll();
	 * 
	 * // assertEquals(response.getStatusCode(), 200); }
	 * 
	 * @Given("put post url") public void put_post_url() { urlPost =
	 * "https://httpbin.org/post"; }
	 * 
	 * @When("send post request and get response") public void
	 * send_post_request_and_get_response() { File file = new
	 * File("src/main/resources/Simple.json"); RequestSpecification rs =
	 * RestAssured.given(); rs.body(file);
	 * 
	 * resPost = rs.post(urlPost);
	 * 
	 * 
	 * response = RestAssured .given() .header("Content-Type", "application/json")
	 * .body(file) .post(url); response.print();
	 * 
	 * }
	 * 
	 * @Then("validate post data with test cases") public void
	 * validate_post_data_with_test_cases() { SoftAssert sf = new SoftAssert();
	 * sf.assertTrue(resPost.statusCode() == 200); // int/double equal == & String :
	 * equals() sf.assertTrue(resPost.time() < 2000);
	 * sf.assertTrue(resPost.contentType().contains("json"));
	 * sf.assertTrue(!resPost.body().asString().equals(null));
	 * 
	 * sf.assertTrue(resPost.body().asString().contains("Name"));
	 * sf.assertTrue(resPost.body().asString().contains("Salary"));
	 * 
	 * // value = json parser = JsonPath JsonPath jp = resPost.jsonPath();
	 * sf.assertTrue(jp.get("json.Name").equals("Sarower"));
	 * sf.assertTrue(jp.get("json.Salary").equals(5000)); sf.assertAll(); }
	 * 
	 * @Given("put update url") public void put_update_url() { urlPut =
	 * "https://httpbin.org/put"; }
	 * 
	 * @When("send put request and get response") public void
	 * send_put_request_and_get_response() { File file = new
	 * File("src/main/resources/Update.json"); RequestSpecification rs =
	 * RestAssured.given(); rs.body(file);
	 * 
	 * resPut = rs.put(urlPut);
	 * 
	 * 
	 * response = RestAssured .given() .header("Content-Type", "application/json")
	 * .body(file) .put(url); response.print();
	 * 
	 * }
	 * 
	 * @Then("validate put data with test cases") public void
	 * validate_put_data_with_test_cases() { SoftAssert sf = new SoftAssert();
	 * sf.assertTrue(resPut.statusCode() == 200); // int/double equal == & String :
	 * equals() sf.assertTrue(resPut.time() < 2000);
	 * sf.assertTrue(resPut.contentType().contains("json"));
	 * sf.assertTrue(!resPut.body().asString().equals(null));
	 * 
	 * sf.assertTrue(resPut.body().asString().contains("Name"));
	 * sf.assertTrue(resPut.body().asString().contains("Salary"));
	 * 
	 * // value = json parser = JsonPath JsonPath jp = resPut.jsonPath();
	 * sf.assertTrue(jp.get("json.Name").equals("David"));
	 * sf.assertTrue(jp.get("json.Salary").equals(15000)); sf.assertAll();
	 * 
	 * // assertEquals(response.getStatusCode(), 200); }
	 * 
	 * @Given("put delete url") public void put_delete_url() { urlTrash =
	 * "https://httpbin.org/delete"; }
	 * 
	 * @When("send delete request and get response") public void
	 * send_delete_request_and_get_response() { resDelete =
	 * RestAssured.delete(urlTrash);
	 * 
	 * // response = RestAssured.delete(url); // response.print(); }
	 * 
	 * @Then("validate delete data with test cases") public void
	 * validate_delete_data_with_test_cases() { SoftAssert sf = new SoftAssert();
	 * sf.assertTrue(resDelete.statusCode() == 200);
	 * sf.assertEquals(resDelete.statusCode(), 200); sf.assertTrue(resDelete.time()
	 * < 2000); sf.assertTrue(resDelete.contentType().contains("json"));
	 * sf.assertTrue(!resDelete.body().equals(null));
	 * sf.assertTrue(resDelete.body().asString().contains("json")); JsonPath jp =
	 * resDelete.jsonPath(); try {
	 * sf.assertTrue(jp.get("json").toString().equals("null")); } catch (Exception
	 * e) {
	 * 
	 * } sf.assertAll();
	 * 
	 * // assertEquals(response.getStatusCode(), 200); }
	 */
}
