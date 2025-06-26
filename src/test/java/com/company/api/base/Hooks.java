package com.company.api.base;

import com.company.util.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.getInstance();
        ExtentReportManager.scenario = ExtentReportManager.extent
                .createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.scenario.fail("Test Failed");
        } else {
            ExtentReportManager.scenario.pass("Test Passed");
        }
        ExtentReportManager.extent.flush();
    }
}
