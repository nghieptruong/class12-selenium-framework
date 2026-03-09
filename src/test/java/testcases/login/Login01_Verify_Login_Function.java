package testcases.login;

import base.BaseTest;
import constants.TimeOutConstants;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CommonDialog;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;
import utils.ExcelUtils;

import java.time.Duration;

public class Login01_Verify_Login_Function extends BaseTest {

    @DataProvider(name = "login-credentials")
    public Object[][] loginCredentialsData() {
        Object[][] data = ExcelUtils.readAllData(System.getProperty("user.dir") + "/src/test/resources/testdata/users_credentials.xlsx", "Sheet1");
        return data;
    }

    @Test(description = "Verify valid login successfully", dataProvider = "login-credentials")
    public void testValidLogin(String username, String password) {
//        String account = "57ed1175-e5d9-47a9-836b-ff28011a57ca";

        WebDriver driver = DriverFactory.getDriver();

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CommonDialog commonDialog = new CommonDialog(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Step 1: Go to https://demo1.cybersoft.edu.vn/
        ExtentReportManager.info("Step 1: Go to https://demo1.cybersoft.edu.vn/");
        LOG.info("Step 1: Go to https://demo1.cybersoft.edu.vn/");
        driver.get("https://demo1.cybersoft.edu.vn/");
        driver.manage().window().maximize(); // maximize browser

        //Step 2: Click 'Đăng Nhập' link on the top right
        ExtentReportManager.info("Step 2: Click 'Đăng Nhập' link on the top right");
        LOG.info("Step 2: Click 'Đăng Nhập' link on the top right");
        homePage.getTopBarNavigation().navigateToLoginPage();

        //Step 3: Enter account login
        ExtentReportManager.info("Step 3: Enter account login");
        LOG.info("Step 3: Enter account login");
        loginPage.enterAccount(username);

        //Step 4: Enter password login
        ExtentReportManager.info("Step 4: Enter password login");
        LOG.info("Step 4: Enter password login");
        loginPage.enterPassword(password);

        //Step 5: Click login button
        ExtentReportManager.info("Step 5: Click login button");
        LOG.info("Step 5: Click login button");
        loginPage.clickLoginButton();

        //Step 6: Verify login successfully with valid account
        ExtentReportManager.info("Step 6: Verify login successfully with valid account");
        LOG.info("Step 6: Verify login successfully with valid account");
        //VP1: Check 'Đăng nhập thành công' message display
        ExtentReportManager.info("VP1: Check 'Đăng nhập thành công' message display");
        LOG.info("VP1: Check 'Đăng nhập thành công' message display");
        String recordedLoginMsg = commonDialog.getDialogMessage();
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "'Đăng Nhập Thành Công' message does not display !!!");

        //VP2: User profile name display on the top
        ExtentReportManager.info("VP2: User profile name display on the top");
        LOG.info("VP2: User profile name display on the top");
        //TO-DO: Implement later

        //Log out after test
        commonDialog.waitForDialogNotDisplayed(TimeOutConstants.DEFAULT_TIMEOUT);
        homePage.getTopBarNavigation().logOut();
        homePage.getTopBarNavigation().waitForLoginLinkDisplayed(TimeOutConstants.DEFAULT_TIMEOUT);
    }
}
