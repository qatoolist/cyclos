package com.qaneha.core;

import com.qaneha.utils.TestData;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setupWebDriver() {
        // Choose your desired WebDriver based on your needs (e.g., Chrome, Firefox)
        driver = new ChromeDriver();

        // Set implicit wait for element visibility (optional, adjust value according to your tests)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot(String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + testName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {

            // Check if test failed using TestNG result object
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getMethod().getQualifiedName());
            }

            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        // Instead of hardcoding the file path, consider environment variables or configuration files
        String filePath = "/path/cyclos/src/main/resources/datasheet.xlsx";

        String[] data = TestData.getData(filePath);
        return new Object[][]{{data[0], data[1]}};
    }
}
