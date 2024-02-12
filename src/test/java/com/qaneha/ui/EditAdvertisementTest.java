package com.qaneha.ui;

import com.qaneha.core.BaseTest;
import com.qaneha.pom.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class EditAdvertisementTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void testEditAdvertisement(String username, String password) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();

        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.waitForPageLoad();
        DirectoryPage directoryPage = dashboardPage.clickDirectoryLink();

        MyAdvertisementsPage myAdsPage = directoryPage.clickMyAdvertisementsLink();
        myAdsPage.waitForPageLoad();
        myAdsPage.selectOrderByLastPublished();

        ItemViewPage itemPage = myAdsPage.clickStudioProductionResultItem();
        itemPage.waitForPageLoad();

        EditItemPage editPage = itemPage.clickEditButton();
        editPage.waitForPageLoad();

        // Set new price and publication period
        String newPrice = "101.00"; // Replace with desired price
        String newStartDate = "10/02/2024"; // Replace with desired start date
        String newEndDate = "20/02/2024"; // Replace with desired end date
        editPage.setPrice(newPrice);
        editPage.setPublicationPeriod(newStartDate, newEndDate);


        // Submit changes and take screenshot (optional)
        // ... (implement logic for submitting changes and screenshot capture)

        // Assert if changes are reflected (optional)
        // ... (add assertions to verify edited values)
    }
}
