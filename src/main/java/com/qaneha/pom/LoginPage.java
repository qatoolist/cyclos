package com.qaneha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "input[placeholder='User']")
    private WebElement username;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement password;

    @FindBy(css = "button[class~='justify-content-center']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected boolean identifyPage() {
        return getUrl().contains("/dashboard"); // Adjust based on your URL structure
    }

    public DashboardPage login(String username, String password) {
        sendKeysToElement(this.username, username);
        sendKeysToElement(this.password, password);
        clickElement(submitButton);
        return new DashboardPage(driver);
    }
}
