package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonDialog extends BasePage {

    private By byLblSuccessMsg = By.id("swal2-title");
    private By byBtnAgree = By.xpath("//button[text()='Đồng ý']");

    public CommonDialog(WebDriver driver) {
        super(driver);
    }

    public String getDialogMessage() {
        return getText(byLblSuccessMsg);
    }

    public void waitForDialogNotDisplayed(long timeOutInSec) {
        waitInVisibilityOfElementLocated(byLblSuccessMsg, timeOutInSec);
    }

    public void clickAgreeButton() {
        click(byBtnAgree);
    }
}
