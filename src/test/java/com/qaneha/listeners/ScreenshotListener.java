package com.qaneha.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Get the failed test method name
        String testName = result.getMethod().getQualifiedName();

        // Take a screenshot and save it with the test name
        TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute("driver");
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + testName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
