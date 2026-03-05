package testcases.register;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CommonDialog;
import pages.HomePage;
import pages.LoginPage;
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
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CommonDialog commonDialog = new CommonDialog(driver);

        //Step 1: Go to https://demo1.cybersoft.edu.vn/
        driver.get("https://demo1.cybersoft.edu.vn/");
        driver.manage().window().maximize(); // maximize browser

        //Step 2: Click on "Đăng Ký" link on the top right
        homePage.getTopBarNavigation().navigateToRegisterPage();

        //Step 3: Enter account name
        registerPage.enterAccount(account);

        //Step 4: Enter password
        registerPage.enterPassword("Test123456@");

        //Step 5: Re-enter password
        registerPage.confirmPassword("Test123456@");

        //Step 6: Enter full name
        registerPage.enterFullName("Nghiep Truong");

        //Step 7: Enter email
        registerPage.enterEmail(email);

        //Step 8: Click "Đăng Ký" button
        registerPage.clickRegisterButton();

        //Step 9: Verify that registration is successful by checking the success message
        //VP1 (Verify Point): Check 'Đăng Ký Thành Công' message display
        String recordedSuccessMsg = commonDialog.getDialogMessage();

        //Khai bao hard assert. Khi failed, test case stop
        Assert.assertEquals(recordedSuccessMsg, "Đăng ký thành công", "'Đăng Ký Thành Công' message does not display !!!");

        //VP2: Check login successfully by login with the registered account
        //Click on "Đăng Nhập" link on the top right
        homePage.getTopBarNavigation().navigateToLoginPage();

        //Login with registered user above
        loginPage.enterAccount(account);
        loginPage.enterPassword("Test123456@");
        loginPage.clickLoginButton();

        //VP2.1: Check 'Đăng Nhập Thành Công' message display
        String recordedLoginMsg = commonDialog.getDialogMessage();
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "'Đăng Nhập Thành Công' message does not display !!!");

        driver.quit(); // quit driver
        
    }
}
