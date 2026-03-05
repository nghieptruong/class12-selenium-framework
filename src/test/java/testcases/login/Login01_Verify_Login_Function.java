package testcases.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CommonDialog;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class Login01_Verify_Login_Function {

    @Test(description = "Verify valid login successfully")
    public void testValidLogin() {
        String account = "57ed1175-e5d9-47a9-836b-ff28011a57ca";

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CommonDialog commonDialog = new CommonDialog(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Step 1: Go to https://demo1.cybersoft.edu.vn/
        driver.get("https://demo1.cybersoft.edu.vn/");
        driver.manage().window().maximize(); // maximize browser

        //Step 2: Click 'Đăng Nhập' link on the top right
        homePage.getTopBarNavigation().navigateToLoginPage();

        //Step 3: Enter account login
        loginPage.enterAccount(account);

        //Step 4: Enter password login
        loginPage.enterPassword("Test123456@");

        //Step 5: Click login button
        loginPage.clickLoginButton();

        //Step 6: Verify login successfully with valid account
        //VP1: Check 'Đăng nhập thành công' message display
        String recordedLoginMsg = commonDialog.getDialogMessage();
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "'Đăng Nhập Thành Công' message does not display !!!");


        //VP2: User profile name display on the top

        driver.quit();
    }
}
