package anhtester.pages;

import anhtester.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddNewUserDialog {
    WebDriver driver;
    JavascriptExecutor js;
    private Actions actions;
    private ValidateHelper validateHelper;
    private By headerPage = By.xpath("//h6[normalize-space()='Add User']");
    private By userRoleDropdown = By.xpath("//label[contains(text(),'User Role')]/following::div[contains(@class,'oxd-select-text')]");
    private By userRoleDropdownValue = By.xpath("//div[@role='option' and normalize-space()='ESS']");
    private By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private By employeeNameValue = By.xpath("//div[@role='listbox' and normalize-space()='Orange Test']");//gợi ý trong div có nhiều option nhưng bị ẩn
    private By statusDropdown = By.xpath("//label[contains(text(),'Status')]/following::div[contains(@class,'oxd-select-text')]");
    private By statusDropdownValue = By.xpath("//div[@role='option' and normalize-space()='Disabled']");
    private By userNameInput = By.xpath("//label[normalize-space()='Username']//following::input");
    private By passInput = By.xpath("//label[normalize-space()='Password']//following::input");
    private By confirmPassInput = By.xpath("//label[normalize-space()='Confirm Password']//following::input");
    private By btnSaveAddUser = By.xpath("//button[normalize-space()='Save']");
    public AddNewUserDialog(WebDriver driver)
    {
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }

    public void saveUser () throws InterruptedException {
        Assert.assertTrue(validateHelper.verifyElementText(headerPage,"Add User"));

        validateHelper.clickElement(userRoleDropdown);
        validateHelper.clickElement(userRoleDropdownValue);
        Thread.sleep(1000);
        //js.executeScript("arguments[0].click();", driver.findElement(employeeNameInput));
        validateHelper.clickElement(employeeNameInput);
        validateHelper.setText(employeeNameInput,"Orange");

        //Thread.sleep(2000);
        // Cách 1: Nhập "Orange" -> chọn keyboard dấu xuống -> nhấn enter (nhược điểm: ko biết chắc expected value mình mong muốn)
//        actions = new Actions(driver);
//        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
//        actions.sendKeys(Keys.ENTER).build().perform();

        // Cách 2: Lấy đường dẫn xpath mới có giá trị cần truyền vào
        validateHelper.clickElement(employeeNameValue);

        Thread.sleep(2000);

        validateHelper.clickElement(statusDropdown);
        validateHelper.clickElement(statusDropdownValue);
        Thread.sleep(1000);

        validateHelper.setText(userNameInput, "Admin113344");
        Thread.sleep(1000);

        validateHelper.setText(passInput,"admin123");
        validateHelper.setText(confirmPassInput, "admin123");
        Thread.sleep(1000);

        validateHelper.clickElement(btnSaveAddUser);

    }

}
