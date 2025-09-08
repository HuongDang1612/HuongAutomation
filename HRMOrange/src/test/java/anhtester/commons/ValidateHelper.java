package anhtester.commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static java.time.Duration.*;
import static org.testng.Assert.assertTrue;

public class ValidateHelper {
    private WebDriver driver;
    private Actions actions;
    private final int timeoutWaitForPageLoaded = 20;
    private WebDriverWait wait;
    JavascriptExecutor js;

    public ValidateHelper(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    public boolean verifyUrl(String url) {
        waitForPageLoaded();
        System.out.println(driver.getCurrentUrl());
        System.out.println(url);
        return driver.getCurrentUrl().contains(url);
    }

    public void setText(By element, String value) {
        //
        waitForPageLoaded();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void clickElement(By element) {
        waitForPageLoaded();
        //click vào 1 phần tử element truyền vào
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //Cách 1: click bằng selenium
        driver.findElement(element).click();

    }

    public void clickElementWithjs(By element) {
        waitForPageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        js.executeScript("arguments[0].click();", driver.findElement(element));

    }

    public void rightClickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.contextClick().build().perform();
    }

    public void selectOptionByText(By element, String text) {
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);

    }

    public void selectOptionByValue(By element, String value) {
        Select select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void selectOptionByIndex(By element, int index) {
        Select select = new Select(driver.findElement(element));
        select.selectByIndex(index);
    }

    public void verifyOptionTotal(By element, int total)
    {
        Select select = new Select(driver.findElement(element));
        Assert.assertEquals(total, select.getOptions().size());
    }

    public void messageAfterSave(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //Click
        WebElement msg = driver.findElement(element);

        assertTrue(msg.getText().trim().equals("Success"), "Failed. No redirect");
    }


    public boolean verifyElementText(By element, String valueText) {
        // Chuyển từ đối tượng By sang đối tượng WebElement thì thêm driver.findElement
        return driver.findElement(element).getText().equals(valueText);

    }

    public boolean verifyElementExist(By element) {
        // Tạo đối tượng chứa tất cả đối tượng WebElement
        List<WebElement> listElement = driver.findElements(element);

        int total = listElement.size();
        if (total > 0) {
            return true;
        }
        return false;
    }


    public void waitForPageLoaded() {
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return JQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for js to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(driver, ofSeconds(timeoutWaitForPageLoaded));
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}