package anhtester.pages;

import anhtester.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {
    WebDriver driver;
    private ValidateHelper validateHelper;
    private AdminPage projectPage;
    private String url = "/web/index.php/dashboard/index";
    private By elementPageText = By.xpath("//p[normalize-space()='Time at Work']");
    private By projectMenu = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > aside:nth-child(1) > nav:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(2)");

    public DashboardPage(WebDriver driver)
    {
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }

     public AdminPage openAdminPage()
     {
         validateHelper.waitForPageLoaded();
         Assert.assertTrue(validateHelper.verifyUrl(url),"Không phải trang Dashboard");
         Assert.assertTrue(validateHelper.verifyElementText(elementPageText,"Time at Work"), "Không phải nội dung này");
         //driver.findElement(dashboardPage).click();
         validateHelper.clickElement(projectMenu);
         return new AdminPage(driver);
     }
}
