package base;

import drivers.ChromeDriverManager;
import drivers.DriverFactory;
import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void beforeClass() {
        DriverManager driverManager = new ChromeDriverManager();
        driverManager.createWebDriver();
        WebDriver driver = driverManager.getDriver();
        DriverFactory.setDriverThreadLocal(driver);
    }

    @AfterClass
    public void afterClass() {
        WebDriver driver = DriverFactory.getDriver();
        if(driver != null) {
            driver.quit();
            // remove thread driver local
            // ...
        }
    }
}
