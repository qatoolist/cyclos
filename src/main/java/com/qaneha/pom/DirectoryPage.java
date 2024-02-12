package com.qaneha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DirectoryPage extends BasePage {

    @FindBy(css = "a[href*='self/simple/list'] div")
    private WebElement myAdvertisementsLink;

    @Override
    protected boolean identifyPage() {
        return getUrl().contains(this.url); // Adjust based on your URL structure
    }

    public DirectoryPage(WebDriver driver) {
        super(driver);
        this.url="https://demo.cyclos.org/ui/users/search";
    }

    public MyAdvertisementsPage clickMyAdvertisementsLink() {
        clickElement(myAdvertisementsLink);
        return new MyAdvertisementsPage(driver);
    }
}
