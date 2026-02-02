package com.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"json:target/cucumber.json"}, // for report
	    features = {"./src/test/resources/features/CRUD.feature"},
	    glue = {"com.api.crud.function"},
	    tags = "@API_Regression", 
	    dryRun = false, // true only when no step def
	    monochrome = true
	)

public class TestRunner extends AbstractTestNGCucumberTests {

}
