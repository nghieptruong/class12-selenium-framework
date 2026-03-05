package drivers;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void createWebDriver() {
        this.driver = new FirefoxDriver();
    }
}
