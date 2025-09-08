package anhtester.pages;

import anhtester.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AdminPage {
    WebDriver driver;
    private ValidateHelper validateHelper;
    private String url = "/web/index.php/admin/viewSystemUsers";
    private By headerPage = By.xpath("//h6[normalize-space()='Admin']");
    private By btnAddUser = By.xpath("//button[normalize-space()='Add']");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }

    public AddNewUserDialog addNewUser()
    {
        //driver.findElement(addProjectBtn).click();
        Assert.assertTrue(validateHelper.verifyUrl(url),"Không phải trang Admin");
        Assert.assertTrue(validateHelper.verifyElementText(headerPage,"Admin"));
        validateHelper.clickElement(btnAddUser);
        return new AddNewUserDialog(driver);
    }
}
