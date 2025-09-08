package anhtester.testcase;

import anhtester.commons.BaseSetup_Custom;
import anhtester.commons.helpers.ExcelHelpers;
import anhtester.pages.DashboardPage;
import anhtester.pages.SignInPage;
import anhtester.commons.BaseSetup;
import anhtester.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest extends BaseSetup{
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private ExcelHelpers excel;

    private By loginHome = By.xpath("//a[@id='btn-login']");
    private By selectDropdown = By.id("dropdown");

    @BeforeClass
    public void setUpBrowser() {

        //Chạy linh hoạt không cần xml với hàm gọi browser - (bỏ extends cho class)
        //driver = new BaseSetup_Custom().setupDriver("chrome");

        driver = getDriver();
        excel = new ExcelHelpers();
    }

    @Test
    public void signInPage() throws Exception {

        excel.setExcelFile("src/test/resources/Book1.xlsx","Sheet1");
        signInPage = new SignInPage(driver);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //Đọc data từ file excel
        signInPage.signIn(excel.getCellData("Username" , 1), excel.getCellData("Password", 1) );

        validateHelper.waitForPageLoaded();

        //Đọc nhiều data từ file excel
//         for(int i = 0 ; i < 6; i++)
//         {
//             signInPage.signIn(excel.getCellData("username", i), excel.getCellData("password", i));
//         }

        //Ghi data vào file excel
       // excel.setCellData("anhtester.com",5, 0);

        //dashboardPage = signInPage.signIn("Admin", "admin123");

        Thread.sleep(2000);

    }

//    @AfterTest
//    public void closeBrowser() {
//        driver.quit();
//    }
}

