package anhtester.pages;

import anhtester.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

public class MessageAlert {
    WebDriver driver;
    private ValidateHelper validateHelper;
    private By msgAlert = By.xpath("//div[@id='oxd-toaster_1']//div[contains(@class,'Success')]");

    public MessageAlert(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }
    public void verifyMessage()
    {
//        WebElement alert = driver.findElement(By.xpath("//div[@id='oxd-toaster_1']//p"));
//        String message = alert.getText();
//        System.out.println("Alert message: " + message);

        //Click
        WebElement msg = driver.findElement(msgAlert);

        assertTrue(msg.getText().trim().equals("Success"), "Failed. No redirect");
    }
}
