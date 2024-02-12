package com.qaneha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = "a[href='/users/search']")
    private WebElement directoryLink;

    @FindBy(css = "a[href='https://documentation.cyclos.org']")
    private WebElement documentationLink;

    @FindBy(id ="logout-trigger")
    private WebElement logout;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.url = "https://demo.cyclos.org/ui/dashboard";
    }

    @Override
    protected boolean identifyPage() {
        return getUrl().contains(this.url); // Adjust based on your URL structure
    }

    public DirectoryPage clickDirectoryLink() {
        clickElement(directoryLink);
        return new DirectoryPage(driver);
    }

    public void clickDocumentationLink() {
        clickElement(documentationLink);
    }

    public void logout(){
        clickElement(logout);
    }
}
