package anhtester.testcase;

import anhtester.commons.BaseSetup;
import anhtester.commons.ValidateHelper;
import anhtester.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchAdminTest extends BaseSetup {
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private SignInPage signInPage;
    private AdminPage adminPage;
    private DashboardPage dashboardPage;
    private AddNewUserDialog addNewUserDialog;
    private ViewSystemUsers viewSystemUsers;

    // private By headerPage = By.xpath("//a[@id='btn-login']");
    //private By selectDropdown = By.id("dropdown");

    @BeforeClass
    public void setUpBrowser() {
        driver = getDriver();
    }

    @Test(priority = 1)
    public void signInPage() throws InterruptedException {

        validateHelper = new ValidateHelper(driver);
        signInPage = new SignInPage(driver);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        dashboardPage = signInPage.signIn("Admin", "admin123");

        Thread.sleep(2000);

    }
    @Test(priority = 2)
    public void openAdminPage() throws InterruptedException {
        validateHelper = new ValidateHelper(driver);

        adminPage = dashboardPage.openAdminPage();

        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void searchUser() throws InterruptedException {
        validateHelper = new ValidateHelper(driver);

        validateHelper.waitForPageLoaded();

        viewSystemUsers.enterSearchValue("admin");
        Thread.sleep(2000);
        viewSystemUsers.checkSearchTableByColumn(3, "admin");

        Thread.sleep(2000);
        //return new MessageAlert(driver);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}

