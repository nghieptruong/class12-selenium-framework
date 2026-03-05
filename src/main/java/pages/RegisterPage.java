package pages;

import constants.TimeOutConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends CommonPage {
    //Khai bao locators
    private By byTxtAccount = By.id("taiKhoan");
    private By byTxtPassword = By.id("matKhau");
    private By byTxtRePassword = By.id("confirmPassWord");
    private By byTxtFullName = By.id("hoTen");
    private By byTxtEmail = By.id("email");
    private By byBtnRegister = By.xpath("//button[span[text()='Đăng ký']]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccount(String accountName, long timeOutInSec) {
        inputText(byTxtAccount, accountName, timeOutInSec);
    }

    public void enterAccount(String accountName) {
        enterAccount(accountName, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void enterPassword(String password, long timeOutInSec) {
        inputText(byTxtPassword, password, timeOutInSec);
    }

    public void enterPassword(String password) {
        enterPassword(password, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void confirmPassword(String password, long timeOutInSec) {
        inputText(byTxtRePassword, password, timeOutInSec);
    }

    public void confirmPassword(String password) {
        confirmPassword(password, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void enterFullName(String fullName, long timeOutInSec) {
        inputText(byTxtFullName, fullName, timeOutInSec);
    }

    public void enterFullName(String fullName) {
        enterFullName(fullName, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void enterEmail(String email, long timeOutInSec) {
        inputText(byTxtEmail, email, timeOutInSec);
    }

    public void enterEmail(String email) {
        enterEmail(email, TimeOutConstants.DEFAULT_TIMEOUT);
    }

    public void clickRegisterButton(long timeOutInSec) {
        click(byBtnRegister, timeOutInSec);
    }

    public void clickRegisterButton() {
        clickRegisterButton(TimeOutConstants.DEFAULT_TIMEOUT);
    }
}
