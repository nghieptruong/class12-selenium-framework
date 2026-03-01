package testcases.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

import java.time.Duration;
import java.util.UUID;

public class Register_01_Verify_Register_Function {

    @Test(description = "Verify user can register successfully")
    public void testValidRegister() {
        String account = UUID.randomUUID().toString();
        System.out.println(account);
        String email = account + "@example.com";

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        //khai báo pages
        RegisterPage registerPage = new RegisterPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Step 1: Go to https://demo1.cybersoft.edu.vn/
        driver.get("https://demo1.cybersoft.edu.vn/");
        driver.manage().window().maximize(); // maximize browser

        //Step 2: Click on "Đăng Ký" link on the top right
        By byLnkRegister = By.xpath("//a[@href='/sign-up']");
        WebElement lnkRegister = wait.until(ExpectedConditions.visibilityOfElementLocated(byLnkRegister));
        lnkRegister.click();

        //Step 3: Enter account name
        registerPage.enterAccount(account);

        //Step 4: Enter password
        By byTxtPassword = By.id("matKhau");
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtPassword));
        txtPassword.sendKeys("Test123456@");

        //Step 5: Re-enter password
        By byTxtRePassword = By.id("confirmPassWord");
        WebElement txtRePassword = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtRePassword));
        txtRePassword.sendKeys("Test123456@");

        //Step 6: Enter full name
        By byTxtFullName = By.id("hoTen");
        WebElement txtFullName = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtFullName));
        txtFullName.sendKeys("Nghiep Truong");

        //Step 7: Enter email
        By byTxtEmail = By.id("email");
        WebElement txtEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtEmail));
        txtEmail.sendKeys(email);

        //Step 8: Click "Đăng Ký" button
        By byBtnRegister = By.xpath("//button[span[text()='Đăng ký']]");
        WebElement btnRegister = wait.until(ExpectedConditions.elementToBeClickable(byBtnRegister));
        btnRegister.click();

        //Step 9: Verify that registration is successful by checking the success message
        //VP1 (Verify Point): Check 'Đăng Ký Thành Công' message display
        By byLblSuccessMsg = By.id("swal2-title");
        WebElement lblSuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblSuccessMsg));
        String recordedSuccessMsg = lblSuccessMsg.getText();
//        if(recordedSuccessMsg.equals("Đăng ký thành công")) {
//            System.out.println("VP1: Register successfully - PASSED");
//        } else {
//            System.out.println("VP1: Register successfully - FAILED");
//        }
        //Khai bao hard assert. Khi failed, test case stop
        Assert.assertEquals(recordedSuccessMsg, "Đăng ký thành công", "'Đăng Ký Thành Công' message does not display !!!");

//        //Khai bao soft assert. Khi failed, tiep tuc chay cho den khi gap assertAll
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(recordedSuccessMsg, "Đăng ký thành công123", "'Đăng Ký Thành Công' message does not display !!!");
//        System.out.println("Hello World");
//        softAssert.assertAll();

        //VP2: Check login successfully by login with the registered account
        //Click on "Đăng Nhập" link on the top right
        By byLnkLogin = By.xpath("//a[h3[text()='Đăng Nhập']]");
        WebElement lnkLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byLnkLogin));
        lnkLogin.click();

        //Login with registered user above
        By byTxtAccountLogin = By.id("taiKhoan");
        WebElement txtAccountLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtAccountLogin));
        txtAccountLogin.sendKeys(account);

        By byTxtPasswordLogin = By.id("matKhau");
        WebElement txtPasswordLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byTxtPasswordLogin));
        txtPasswordLogin.sendKeys("Test123456@");

        By byBtnLogin = By.xpath("//button[span[text()='Đăng nhập']]");
        WebElement btnLogin = wait.until(ExpectedConditions.elementToBeClickable(byBtnLogin));
        btnLogin.click();

        //VP2.1: Check 'Đăng Nhập Thành Công' message display
        By byLblLoginMsg = By.id("swal2-title");
        WebElement lblLoginMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblLoginMsg));
        String recordedLoginMsg = lblLoginMsg.getText();
//        if(recordedLoginMsg.equals("Đăng nhập thành công")) {
//            System.out.println("VP2: Login successfully - PASSED");
//        } else {
//            System.out.println("VP2: Login successfully - FAILED");
//        }
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "'Đăng Nhập Thành Công' message does not display !!!");

        driver.quit(); // quit driver
        
    }
}
