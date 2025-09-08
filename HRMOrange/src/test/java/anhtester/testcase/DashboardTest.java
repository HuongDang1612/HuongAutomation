package anhtester.testcase;

import anhtester.commons.ValidateHelper;
import anhtester.pages.DashboardPage;
import anhtester.pages.AdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends SignInTest {
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private DashboardPage dashboardPage;
    private AdminPage projectPage;

    private By titlePage = By.xpath("project-");
    private By addNewBtn = By.id("dropdown");

    @Test
    public void abc() throws InterruptedException {
        validateHelper = new ValidateHelper(driver);

        projectPage = dashboardPage.openAdminPage();
        Assert.assertTrue(validateHelper.verifyElementText(titlePage,"Project"), "Không phải là trang project");

        validateHelper.clickElement(addNewBtn);

        Thread.sleep(2000);
        driver.quit();
    }
}

