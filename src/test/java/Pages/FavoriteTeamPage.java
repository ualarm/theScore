package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FavoriteTeamPage extends BasePage {
    private By continueButton = By.id("com.fivemobile.thescore:id/btn_primary");
    private By view_group_favorite_4  = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]");

    public FavoriteTeamPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    public void selectTeamAndContinue() {
        click(view_group_favorite_4);
        click(continueButton);
    }
}
