package anhtester.pages;

import anhtester.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignInPage {
    WebDriver driver;
    private ValidateHelper validateHelper;
    private By emailInput = By.xpath("//input[@placeholder='Username']");
    private By passInput = By.xpath("//input[@placeholder='Password']");
    private By loginBtn = By.xpath("//button[normalize-space()='Login']");
    private By signInText = By.xpath("//button[normalize-space()='Login']");


    public SignInPage(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }

    public DashboardPage signIn(String email, String password) {
        validateHelper.waitForPageLoaded();
        //Kiểm tra text của nút Sign-in có đúng là text Signin ko
        Assert.assertTrue(validateHelper.verifyElementText(signInText, "Login"), "Không phải tên nút là Login");
        validateHelper.setText(emailInput, email);
        validateHelper.setText(passInput, password);
        validateHelper.clickElement(loginBtn);

        return new DashboardPage(driver);
    }
}
