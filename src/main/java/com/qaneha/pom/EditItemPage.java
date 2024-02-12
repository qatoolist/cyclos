package com.qaneha.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditItemPage extends BasePage {

    @FindBy(xpath = "(//button[@type='button'])[5]")
    private WebElement priceInput;

    @FindBy(id = "id_38")
    private WebElement publicationPeriodStart;

    @FindBy(id = "id_39")
    private WebElement publicationPeriodEnd;

    public EditItemPage(WebDriver driver) {
        super(driver);
        this.url = "https://demo.cyclos.org/ui/marketplace/edit/7762070814175099199";
    }

    @Override
    protected boolean identifyPage() {
        return getUrl().contains(this.url); // Adjust based on your URL structure
    }

    public void setPrice(String price) {
        sendKeysToElement(priceInput, price);
    }

    private void setPublicationPeriodStart(String date) {
        sendKeysToElement(publicationPeriodStart, date);
    }

    private void setPublicationPeriodEnd(String date) {
        sendKeysToElement(publicationPeriodEnd, date);
    }

    public void setPublicationPeriod(String start_date, String end_date) {
        setPublicationPeriodStart(start_date);
        setPublicationPeriodEnd(end_date);
    }
}
