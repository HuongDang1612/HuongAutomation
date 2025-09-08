package anhtester.pages;
import anhtester.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


public class ViewSystemUsers {
    WebDriver driver;
    private ValidateHelper validateHelper;
    private By usernameSearchBtn = By.xpath("//label[normalize-space()='Username']/ancestor::div[contains(@class,'oxd-input-group')]/following-sibling::div//input");
    private By searchBtn = By.xpath("//button[normalize-space()='Search']");

    public ViewSystemUsers(WebDriver driver) {
        this.driver = driver;
        validateHelper = new ValidateHelper(driver);
    }
    public void enterSearchValue(String value)
    {
        validateHelper.setText(usernameSearchBtn, value);
        validateHelper.clickElement(searchBtn);
    }

    public void checkSearchTableByColumn(int column, String value)
    {
        //Xác định số dòng của table sau khi search
        List<WebElement> row = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']//div[@role='row']//div[@role='cell']"));
        //(By.xpath("//table//tbody/td"));

        int totalrow = row.size(); //Lấy ra số dòng

        //Duyệt từng dòng
        for(int i = 1; i <= totalrow; i++)
        {
            WebElement elementCheck = driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']//div[@role='row'][" + i +"]//div[@role='cell'][" + column + "]"));
            //(By.xpath("//table//tbody/tr["+ i +"]/td["+ column +"]"));

            //Cuộn tới trang xuống tới phần tử tìm kiếm nếu chị che khuất
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            System.out.print(value + " - ");
            System.out.println(elementCheck.getText());

            Assert.assertTrue(elementCheck.getText().contains(value), "Không tìm thấy giá trị tìm kiếm");
        }

    }

}
