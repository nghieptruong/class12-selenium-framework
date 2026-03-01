package testcases.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login01_Verify_Login_Function {

    @Test(description = "Verify valid login successfully")
    public void testValidLogin() {
        String account = "57ed1175-e5d9-47a9-836b-ff28011a57ca";

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Step 1: Go to https://demo1.cybersoft.edu.vn/
        driver.get("https://demo1.cybersoft.edu.vn/");
        driver.manage().window().maximize(); // maximize browser

        //Step 2: Click 'Đăng Nhập' link on the top right
        By byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
        WebElement lnkLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byLnkLogin));
        lnkLogin.click();

        //Step 3: Enter account login
        By byTxtAccountLogin = By.id("taiKhoan");
        WebElement txtAccountLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtAccountLogin));
        txtAccountLogin.sendKeys(account);

        //Step 4: Enter password login
        By byTxtPasswordLogin = By.id("matKhau");
        WebElement txtPasswordLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtPasswordLogin));
        txtPasswordLogin.sendKeys("Test123456@");

        //Step 5: Click login button
        By byBtnLogin = By.xpath("//button[span[text()='Đăng nhập']]");
        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(byBtnLogin));
        btnLogin.click();

        //Step 6: Verify login successfully with valid account
        //VP1: Check 'Đăng nhập thành công' message display
        By byLblLoginMsg = By.id("swal2-title");
        WebElement lblLoginMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblLoginMsg));
        String recordedLoginMsg = lblLoginMsg.getText();
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "'Đăng Nhập Thành Công' message does not display !!!");


        //VP2: User profile name display on the top

    }
}
