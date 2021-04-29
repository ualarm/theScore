package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetStartedPage extends BasePage {
    private By getStartedButton = By.id("com.fivemobile.thescore:id/action_button_text");

    public GetStartedPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    public void clickGetStarted() {
        click(getStartedButton);
    }
}
