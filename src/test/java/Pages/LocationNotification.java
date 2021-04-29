package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationNotification extends BasePage {

    private By allowLocationButton = By.id("com.fivemobile.thescore:id/btn_allow");
    private By allowWhenUsingByButton = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");

    public LocationNotification(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    public void allowLocation() {
        waitPresent(allowLocationButton);
        if (waitVisible(allowLocationButton).isDisplayed()) {
            waitVisible(allowLocationButton).click();
            if (waitVisible(allowWhenUsingByButton).isDisplayed()) {
                waitVisible(allowWhenUsingByButton).click();
            }
        }
    }
}
