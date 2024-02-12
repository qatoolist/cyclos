package com.qaneha.core;

import com.qaneha.utils.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setupWebDriver() {
        // Choose your desired WebDriver based on your needs (e.g., Chrome, Firefox)
        driver = new ChromeDriver();

        // Set implicit wait for element visibility (optional, adjust value according to your tests)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void navigateToBaseUrl() {
        // Replace with your base URL
        driver.get("https://your-base-url.com");
    }

    @AfterClass
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginData() throws IOException {
        // Instead of hardcoding the file path, consider environment variables or configuration files
        String filePath = System.getProperty("loginData.xlsx") != null ? System.getProperty("loginData.xlsx") : "path/to/your/excel.xlsx";

        List<TestData> data = TestData.readFromExcel(filePath);
        List<Object[]> testData = new ArrayList<>();

        for (TestData testDatum : data) {
            testData.add(new Object[]{testDatum.getUsername(), testDatum.getPassword()});
        }

        return testData.iterator();
    }
}
