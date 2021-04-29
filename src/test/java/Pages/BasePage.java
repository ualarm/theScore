package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage (WebDriver driver, WebDriverWait w) {
        this.driver = driver;
        this.wait = w;
    }
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected WebElement waitPresent(By location) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(location));
    }

    protected WebElement waitVisible(By location) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(location));
    }

    protected void type(String text, By locator) {
        waitVisible(locator).sendKeys(text);
    }

    protected void click(By locator) {
        waitVisible(locator).click();
    }
}
