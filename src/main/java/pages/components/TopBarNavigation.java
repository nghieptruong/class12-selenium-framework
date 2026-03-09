package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CommonDialog;

public class TopBarNavigation extends BasePage {

    private By byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
    private By byLnkRegister = By.xpath("//a[@href='/sign-up']");
    private By byLnkLogout = By.xpath("//a[h3[text()='Đăng xuất']]");

    public TopBarNavigation(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        click(byLnkLogin);
    }

    public void navigateToRegisterPage() {
        click(byLnkRegister);
    }

    public void clickLogOut() {
        click(byLnkLogout);
    }

    public void logOut() {
        clickLogOut();
        CommonDialog commonDialog = new CommonDialog(driver);
        commonDialog.clickAgreeButton();
    }

    public WebElement waitForLoginLinkDisplayed(long timeOutInSec) {
        return waitVisibilityOfElementLocated(byLnkLogin, timeOutInSec);
    }
}
