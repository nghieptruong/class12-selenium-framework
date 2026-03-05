package drivers;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriverManager();
        } else if(browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriverManager();
        } else if(browser.equalsIgnoreCase("safari")) {
            return new SafariDriverManager();
        } else if(browser.equalsIgnoreCase("edge")) {
            return new EdgeDriverManager();
        } else {
            return null;
        }
    }
}
