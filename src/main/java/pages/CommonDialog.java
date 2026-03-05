package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonDialog extends BasePage {

    private By byLblSuccessMsg = By.id("swal2-title");

    public CommonDialog(WebDriver driver) {
        super(driver);
    }

    public String getDialogMessage() {
        return getText(byLblSuccessMsg);
    }
}
