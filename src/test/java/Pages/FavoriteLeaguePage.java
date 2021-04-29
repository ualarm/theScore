package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FavoriteLeaguePage extends BasePage {

    private By continueButton = By.id("com.fivemobile.thescore:id/btn_primary");

    public FavoriteLeaguePage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
