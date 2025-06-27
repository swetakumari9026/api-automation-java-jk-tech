package com.company.api.base;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "src/test/java/com/company/api/stepdef",
        plugin = {"pretty", "html:target/cucumber-html-report.html"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {


}
