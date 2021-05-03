import Pages.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class theScoreTest {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait                wait;
    private final String dataFileDir = "testdata";

    @Parameters({"deviceName", "deviceId", "searchCategory", "searchCriteria"})
    private void printInfo(String deviceName, String deviceId, String searchCategory, String searchCriteria) {
        System.out.println();
        System.out.println("deviceName: " + deviceName);
        System.out.println("deviceID: " + deviceId);
        System.out.println("searchCriteria: " + searchCriteria);
        System.out.println("searchCategory: " + searchCategory);
    }

    @BeforeMethod
    @Parameters({"deviceName", "deviceId"})
    public void setup(String deviceName, String deviceId) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("udid", deviceId); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.fivemobile.thescore");
        caps.setCapability("adbExecTimeout", 120000);
        caps.setCapability("appWaitDuration", 120000);
        caps.setCapability("appium:uiautomator2ServerLaunchTimeout", 180000);
        caps.setCapability("appActivity", "com.fivemobile.thescore.ui.MainActivity");
        caps.setCapability("appWaitActivity", "*");
        caps.setCapability("noReset", "false");
        caps.setCapability("newCommandTimeout", 120);
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 120);

        System.out.println("Started Android Driver successfully");
    }

    public void location_permission_and_favorite() {
        try {
            // click getStarted
            new GetStartedPage(driver, wait).clickGetStarted();

            // select league and click continue
            new FavoriteTeamPage(driver, wait).selectTeamAndContinue();
            new LocationNotification(driver, wait).allowLocation();
            new FavoriteLeaguePage(driver, wait).clickContinue();
            new FavoriteSettingPage(driver, wait).clickDone();
            System.out.println("Added favorite successfully");
        }
        catch(Exception e) {
            String msg = "Exception in location_permission_and_favorite: " + e.getMessage();
            System.out.println(msg);
            Assert.fail(msg);
        }
    }

    @Test(retryAnalyzer = Retry.class)
    @Parameters({"deviceName", "deviceId", "searchCategory", "searchCriteria"})
    public void theScoreTest1(String deviceName, String deviceId, String searchCategory, String searchCriteria) {
        printInfo(deviceName, deviceId, searchCategory, searchCriteria);
        location_permission_and_favorite();

        try {
            // click and enter search value
            SearchPage s = new SearchPage(driver, wait);
            s.clickAndEnterSearchText(searchCriteria);
            // click team/player tab, then click first search result
            s.clickTabAndFirstResult(searchCategory);
            System.out.println("Finished search and clicked on first search result");

            SearchResultDetailPage r = new SearchResultDetailPage(driver, wait);
            r.verifyResultDetail(searchCategory, searchCriteria);
            System.out.println("Verified team/player details");

            // press back and verify it is back to the page with search input
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            Assert.assertTrue(new SearchPage(driver, wait).isSearchPage());
            System.out.println("Navigated back to search page");
        }
        catch(Exception e) {
            String msg = "Exception occurred in theScoreTest: " + e.getMessage();
            System.out.println(msg);
            Assert.fail(msg);
        }

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
