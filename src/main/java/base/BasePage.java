package base;

import constants.TimeOutConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final Logger LOG = LogManager.getLogger(getClass());

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean waitInVisibilityOfElementLocated(By locator, long timeOutInSec) {
        LOG.info("waitInVisibilityOfElementLocated... in " + timeOutInSec + "s");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitVisibilityOfElementLocated(By locator, long timeOutInSec) {
        LOG.info("waitVisibilityOfElementLocated... in " + timeOutInSec + "s");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public WebElement waitElementToBeClickable(By locator, long timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }

    public void inputText(By locator, String value, long timeOutInSec) {
        LOG.info("inputText: " + locator + " with " + value);
        WebElement element = waitVisibilityOfElementLocated(locator, timeOutInSec);
        element.sendKeys(value);
    }

    public void inputText(By locator, String value) {
        inputText(locator, value, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void click(By locator, long timeOutInSec) {
        LOG.info("click: " + locator);
        WebElement element = waitElementToBeClickable(locator, timeOutInSec);
        element.click();
    }

    public void click(By locator) {
        click(locator, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public String getText(By locator, long timeOutInSec) {
        WebElement element = waitVisibilityOfElementLocated(locator, timeOutInSec);
        return element.getText();
    }

    public String getText(By locator) {
        return getText(locator, TimeOutConstants.DEFAULT_TIMEOUT);
    }
}
