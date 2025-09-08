package anhtester.testcase;
import anhtester.commons.BaseSetup;
import anhtester.commons.ValidateHelper;
import anhtester.pages.AddNewUserDialog;
import anhtester.pages.AdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AddNewUserTest extends BaseSetup {
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private AdminPage adminPage;
    private AddNewUserDialog addNewUserDialog;

    private By loginHome = By.xpath("//a[@id='btn-login']");
    private By selectDropdown = By.id("dropdown");

    @Test
    public void abc() throws InterruptedException {
        validateHelper = new ValidateHelper(driver);

        //addProductPage = projectPage.addNewUser();

        Thread.sleep(2000);
        driver.quit();
    }
}

