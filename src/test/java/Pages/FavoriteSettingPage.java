package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FavoriteSettingPage extends BasePage {
    private By doneButton = By.id("com.fivemobile.thescore:id/btn_primary");

    public FavoriteSettingPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    public void clickDone() {
        click(doneButton);
    }
}
