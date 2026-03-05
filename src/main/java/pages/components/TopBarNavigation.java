package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopBarNavigation extends BasePage {

    private By byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
    private By byLnkRegister = By.xpath("//a[@href='/sign-up']");

    public TopBarNavigation(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        click(byLnkLogin);
    }

    public void navigateToRegisterPage() {
        click(byLnkRegister);
    }
}
