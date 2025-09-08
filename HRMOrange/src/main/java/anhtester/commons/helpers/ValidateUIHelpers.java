package anhtester.commons.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class ValidateUIHelpers {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;
    private int timeoutWaitForPageLoaded;


    public void ValidateUIHelpers(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, ofSeconds(5));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

    }

    public String getTitlePage() {
        waitForPageLoaded();
        String title = driver.getTitle();
        return title;
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
