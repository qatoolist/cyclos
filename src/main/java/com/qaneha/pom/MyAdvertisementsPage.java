package com.qaneha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAdvertisementsPage extends BasePage {

    @FindBy(id = "id_20")
    private WebElement orderBySelectList;

    @FindBy(css = ".row.tiled-results")
    private WebElement resultsSection;

    @FindBy(xpath = "//div[normalize-space()='Studio Production']")
    private WebElement studioProductionResultItem;

    public MyAdvertisementsPage(WebDriver driver) {
        super(driver);
        this.url="https://demo.cyclos.org/ui/marketplace/self/simple/list";
    }

    @Override
    protected boolean identifyPage() {
        return getUrl().contains(this.url); // Adjust based on your URL structure
    }

    public void selectOrderByLastPublished() {
        chooseFromDropdownByText(orderBySelectList, "Last Published");
    }

    public ItemViewPage clickStudioProductionResultItem() {
        clickElement(studioProductionResultItem);
        return new ItemViewPage(driver);
    }
}
