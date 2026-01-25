package com.api.crud.function;

import static org.testng.Assert.assertEquals;

import java.io.File;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
// import io.restassured.specification.RequestSpecification;

public class Stepdef {
	String url;
    Response response;
    
	@Given("put url")
	public void put_url() {
		url = "https://httpbin.org/get";
	    
	}

	@When("send get request and response")
	public void send_get_request_and_response() {
		response = RestAssured.get(url);
        response.print();
	}

	@Given("put post url")
	public void put_post_url() {
		url = "https://httpbin.org/post";
	}
	
	@When("send post request and get response")
	public void send_post_request_and_get_response() {
		File file = new File("src/main/resources/Simple.json");
        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(file)
                .post(url);
        response.print();
	}
	
	@Then("validate data with test cases")
	public void validate_data_with_test_cases() {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Given("put update url")
	public void put_update_url() {
		url = "https://httpbin.org/put";
	}

	@When("send put request and get response")
	public void send_put_request_and_get_response() {
		File file = new File("src/main/resources/Update.json");
        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(file)
                .put(url);
        response.print();
	}

	@Then("validate put data with test cases")
	public void validate_put_data_with_test_cases() {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Given("put delete url")
	public void put_delete_url() {
		url = "https://httpbin.org/delete";
	}

	@When("send delete request and get response")
	public void send_delete_request_and_get_response() {
		response = RestAssured.delete(url);
        response.print();
	}

	@Then("validate delete data with test cases")
	public void validate_delete_data_with_test_cases() {
		assertEquals(response.getStatusCode(), 200);
	}
}
