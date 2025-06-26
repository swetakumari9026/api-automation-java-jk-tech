package com.company.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    public static ExtentReports extent;
    public static ExtentTest scenario;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentManual/Spark.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "QATeam");
        }
        return extent;
    }
}
