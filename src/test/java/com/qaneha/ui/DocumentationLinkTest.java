package com.qaneha.ui;

import com.qaneha.core.BaseTest;
import com.qaneha.pom.DashboardPage;
import com.qaneha.pom.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class DocumentationLinkTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void testDocumentationLinkAndLogout(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToPage();
        loginPage.waitForPageLoad();
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.waitForPageLoad();
        dashboardPage.clickDocumentationLink();

        // Switch to documentation window and validate title
        String expectedTitle = "Cyclos Documentation";
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Explicit wait for documentation window to load
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs(expectedTitle));
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Documentation window title mismatch");

        // Switch back to main window and logout
        driver.switchTo().window(parentWindowHandle);
        dashboardPage.logout();
    }
}
