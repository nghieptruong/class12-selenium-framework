package pages;

import constants.TimeOutConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {

    private By byTxtAccountLogin = By.id("taiKhoan");
    private By byTxtPasswordLogin = By.id("matKhau");
    private By byBtnLogin = By.xpath("//button[span[text()='Đăng nhập']]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccount(String accountName, long timeOutInSec) {
        inputText(byTxtAccountLogin, accountName, timeOutInSec);
    }

    public void enterAccount(String accountName) {
        enterAccount(accountName, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void enterPassword(String password, long timeOutInSec) {
        inputText(byTxtPasswordLogin, password, timeOutInSec);
    }

    public void enterPassword(String password) {
        enterPassword(password, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void clickLoginButton(long timeOutInSec) {
        click(byBtnLogin, timeOutInSec);
    }

    public void clickLoginButton() {
        clickLoginButton(TimeOutConstants.DEFAULT_TIMEOUT);
    }
}
