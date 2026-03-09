package testcases.register;

import base.BaseTest;
import constants.TimeOutConstants;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CommonDialog;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import reports.ExtentReportManager;

import java.util.UUID;

public class Register_01_Verify_Register_Function extends BaseTest {

    @Test(description = "Verify user can register successfully")
    public void testValidRegister() {
        String account = UUID.randomUUID().toString();
        System.out.println(account);
        String email = account + "@example.com";

        WebDriver driver = DriverFactory.getDriver();

        //khai báo pages
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CommonDialog commonDialog = new CommonDialog(driver);

        //Step 1: Go to https://demo1.cybersoft.edu.vn/
        ExtentReportManager.info("Step 1: Go to https://demo1.cybersoft.edu.vn/");
        LOG.info("Step 1: Go to https://demo1.cybersoft.edu.vn/");
        driver.get("https://demo1.cybersoft.edu.vn/");
        driver.manage().window().maximize(); // maximize browser

        //Step 2: Click on "Đăng Ký" link on the top right
        ExtentReportManager.info("Step 2: Click on 'Đăng Ký' link on the top right");
        LOG.info("Step 2: Click on Đăng Ký link on the top right");
        homePage.getTopBarNavigation().navigateToRegisterPage();

        //Step 3: Enter account name
        ExtentReportManager.info("Step 3: Enter account name");
        LOG.info("Step 3: Enter account name");
        registerPage.enterAccount(account);

        //Step 4: Enter password
        ExtentReportManager.info("Step 4: Enter password");
        LOG.info("Step 4: Enter password");
        registerPage.enterPassword("Test123456@");

        //Step 5: Re-enter password
        ExtentReportManager.info("Step 5: Re-enter password");
        LOG.info("Step 5: Re-enter password");
        registerPage.confirmPassword("Test123456@");

        //Step 6: Enter full name
        ExtentReportManager.info("Step 6: Enter full name");
        LOG.info("Step 6: Enter full name");
        registerPage.enterFullName("Nghiep Truong");

        //Step 7: Enter email
        ExtentReportManager.info("Step 7: Enter email");
        LOG.info("Step 7: Enter email");
        registerPage.enterEmail(email);

        //Step 8: Click "Đăng Ký" button
        ExtentReportManager.info("Step 8: Click 'Đăng Ký' button");
        LOG.info("Step 8: Click 'Đăng Ký' button");
        registerPage.clickRegisterButton();

        //Step 9: Verify that registration is successful by checking the success message
        ExtentReportManager.info("Step 9: Verify that registration is successful by checking the success message");
        LOG.info("Step 9: Verify that registration is successful by checking the success message");
        //VP1 (Verify Point): Check 'Đăng Ký Thành Công' message display
        ExtentReportManager.info("VP1 (Verify Point): Check 'Đăng Ký Thành Công' message display");
        LOG.info("VP1 (Verify Point): Check 'Đăng Ký Thành Công' message display");
        String recordedSuccessMsg = commonDialog.getDialogMessage();

        //Khai bao hard assert. Khi failed, test case stop
        Assert.assertEquals(recordedSuccessMsg, "Đăng ký thành công", "'Đăng Ký Thành Công' message does not display !!!");
        commonDialog.waitForDialogNotDisplayed(TimeOutConstants.DEFAULT_TIMEOUT);

        //VP2: Check login successfully by login with the registered account
        ExtentReportManager.info("VP2: Check login successfully by login with the registered account");
        LOG.info("VP2: Check login successfully by login with the registered account");
        //Click on "Đăng Nhập" link on the top right
        homePage.getTopBarNavigation().navigateToLoginPage();

        //Login with registered user above
        loginPage.enterAccount(account);
        loginPage.enterPassword("Test123456@");
        loginPage.clickLoginButton();

        //VP2.1: Check 'Đăng Nhập Thành Công' message display
        ExtentReportManager.info("VP2.1: Check 'Đăng Nhập Thành Công' message display");
        LOG.info("VP2.1: Check 'Đăng Nhập Thành Công' message display");
        String recordedLoginMsg = commonDialog.getDialogMessage();
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "'Đăng Nhập Thành Công' message does not display !!!");

    }
}
