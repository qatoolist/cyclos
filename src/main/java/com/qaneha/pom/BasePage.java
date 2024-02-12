package com.qaneha.pom;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BasePage {

    protected WebDriver driver;
    protected String url;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPage(){
        driver.get(this.url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    private ExpectedCondition<Boolean> expectedConditionsForJavascriptCompletion() {
        return driver -> {
            // Combine conditions for clarity and efficiency
            boolean readyStateComplete = driver != null && ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            boolean windowStopped = driver != null && (boolean) ((JavascriptExecutor) driver).executeScript("return window.stop == undefined || window.stop()");
            return readyStateComplete && windowStopped;
        };
    }
    
    protected WebElement waitForElementToBeVisible(WebElement element) {

        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(element));
    }

    protected void clickElement(WebElement element) {
        waitForElementToBeVisible(element).click();
    }

    protected void sendKeysToElement(WebElement element, String text) {
        waitForElementToBeVisible(element).sendKeys(text);
    }

    public boolean waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for standard conditions + javascript execution:
        wait.until(and(
                urlContains(this.url),
                visibilityOfElementLocated(By.cssSelector("body")),
                expectedConditionsForJavascriptCompletion() // custom check
        ));

        return identifyPage();
    }

    public void chooseFromDropdownByText(WebElement selectElement, String text) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(text);
    }

    protected boolean identifyPage() {
        // Implement logic to identify the current page based on unique elements or conditions
        throw new UnsupportedOperationException("Please implement identifyPage logic for your specific page");
    }

}
