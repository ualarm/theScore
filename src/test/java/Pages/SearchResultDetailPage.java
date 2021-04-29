package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchResultDetailPage extends BasePage{

    private By verifyObject_Team = By.id("com.fivemobile.thescore:id/team_name");
    private By verifyObject_Player = By.id("com.fivemobile.thescore:id/txt_player_name");
    private By teamTab_stat = By.xpath("//android.widget.LinearLayout[@content-desc=\"Team Stats\"]");
    private By playerTab_stat = By.xpath("//android.widget.LinearLayout[@content-desc=\"Stats\"]");
    private By verifyTeamStatResult = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]");
    private By verifyPlayerStatResult = By.id("com.fivemobile.thescore:id/cta");

    public SearchResultDetailPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    public void verifyResultDetail(String category, String criteria) {
        if( category.equalsIgnoreCase("team") ) {
            String verifyTeamText = waitVisible(verifyObject_Team).getText();
            Assert.assertTrue(verifyTeamText.contains(criteria));
            click(teamTab_stat);
            String verifyTeamStatResultText = waitVisible(verifyTeamStatResult).getText();
            Assert.assertEquals(verifyTeamStatResultText, "Pace");
        }
        else if( category.equalsIgnoreCase("player")) {
            String verifyPlayerText = waitVisible(verifyObject_Player).getText();
            Assert.assertTrue(verifyPlayerText.contains(criteria));
            click(playerTab_stat);
            String verifyPlayerStatResultText = waitVisible(verifyPlayerStatResult).getText();
            Assert.assertEquals(verifyPlayerStatResultText, "Career Stats");
        }
        else {
            Assert.fail("verify result detail not implemented for " + category);
        }
    }
}
