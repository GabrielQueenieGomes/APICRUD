package com.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = "com.api.crud.function",
	    plugin = {
	        "pretty",
	        "json:target/cucumber.json",
	        "html:target/cucumber-report.html"
	    }
	)

public class TestRunner extends AbstractTestNGCucumberTests {

}
