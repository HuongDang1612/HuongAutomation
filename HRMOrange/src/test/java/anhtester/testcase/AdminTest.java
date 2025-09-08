package anhtester.testcase;

import anhtester.commons.BaseSetup;
import anhtester.commons.BaseSetup_Custom;
import anhtester.commons.ValidateHelper;
import anhtester.commons.helpers.ExcelHelpers;
import anhtester.commons.utilities.PropertiesFile;
import anhtester.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdminTest extends BaseSetup{
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
        //excel = new ExcelHelpers();

       // driver = new BaseSetup_Custom().setupDriver("browser");
    }
//@Test
//    public void Login() throws InterruptedException {
//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//
//        //Đọc data từ file Properties File
//        signInPage.signIn(PropertiesFile.getPropValue("email"), PropertiesFile.getPropValue("password"));
//        Thread.sleep(5000);
//
//    }
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
    public void addNewUser() throws InterruptedException {
        validateHelper = new ValidateHelper(driver);

        addNewUserDialog = adminPage.addNewUser();

        Thread.sleep(2000);
        //return new AddProjectPage(driver);
    }
    @Test(priority = 4)
    public ViewSystemUsers  saveUser() throws InterruptedException {
        validateHelper = new ValidateHelper(driver);

        addNewUserDialog.saveUser();

        Thread.sleep(2000);
        return new ViewSystemUsers (driver);
    }


    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}

