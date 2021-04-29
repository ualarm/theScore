import Pages.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.FileReader;

public class theScoreTest {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait                wait;
    public String searchCriteria;
    public String searchCategory;
    public String deviceName;
    public String deviceId;
    private final String dataFileDir = "testdata";

    private void printInfo() {
        System.out.println();
        System.out.println("deviceName: " + deviceName);
        System.out.println("deviceID: " + deviceId);
        System.out.println("searchCriteria: " + searchCriteria);
        System.out.println("searchCategory: " + searchCategory);
    }

    private void getTestData() {
        JsonParser jsonParser = new JsonParser();
        try (FileReader reader =
                     new FileReader(dataFileDir + File.separator + "theScoreTest.json"))
        {
            //Read test data file
            Object obj = jsonParser.parse(reader);

            JsonObject dataSet = (JsonObject) obj;
            searchCriteria = dataSet.get("searchCriteria").getAsString();
            searchCategory = dataSet.get("searchCategory").getAsString();
            deviceName = dataSet.get("deviceName").getAsString();
            deviceId = dataSet.get("deviceID").getAsString();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        getTestData();
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

    @Test
    public void theScoreTest1() {
        printInfo();
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
