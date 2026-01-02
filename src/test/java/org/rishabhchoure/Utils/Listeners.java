package org.rishabhchoure.Utils;

import org.rishabhchoure.pageobjects.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {
    ExtentTest test;
    AppiumDriver driver;
    ExtentReports extent = ExtentReporterNG.getReporterObject();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        try {
            // 1. Get the driver instance from the test class using reflection
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
            
            // 2. Call the getScreenshot method (inherited from AppiumUtils)
            // Note: Ensure getScreenshot returns the String path to the file
            String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            
            // 3. Add to Extent Report
            test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
    
    // ... implement other methods if needed ...
}