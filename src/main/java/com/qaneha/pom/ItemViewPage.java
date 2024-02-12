package com.qaneha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemViewPage extends BasePage {

    @FindBy(xpath = "(//button[@type='button'])[5]")
    private WebElement editButton;

    public ItemViewPage(WebDriver driver) {
        super(driver);
        this.url="https://demo.cyclos.org/ui/marketplace/view/7762070814175099199";
    }

    @Override
    protected boolean identifyPage() {
        return getUrl().contains(this.url); // Adjust based on your URL structure
    }

    public EditItemPage clickEditButton() {
        clickElement(editButton);
        return new EditItemPage(driver);
    }
}
