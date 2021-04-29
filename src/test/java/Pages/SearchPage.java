package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    private By searchingBy = By.id("com.fivemobile.thescore:id/search_bar_text_view");
    private By searchByText = By.id("com.fivemobile.thescore:id/search_src_text");
    private By searchResult_TeamTab = By.xpath("//android.widget.LinearLayout[@content-desc=\"Teams\"]");
    private By searchResult_PlayerTab = By.xpath("//android.widget.LinearLayout[@content-desc=\"Players\"]");
    private By firstResult = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]");

    public SearchPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    public void clickAndEnterSearchText(String w) {
        click(searchingBy);
        type(w, searchByText);
    }

    public void clickTabAndFirstResult(String which) {
        if( which.equalsIgnoreCase("team") )
            click(searchResult_TeamTab);
        else if( which.equalsIgnoreCase("player") )
            click(searchResult_PlayerTab);
        click(firstResult);
    }

    public boolean isSearchPage() {
        return find(searchResult_TeamTab).isDisplayed() && find(searchResult_PlayerTab).isDisplayed();
    }
}
