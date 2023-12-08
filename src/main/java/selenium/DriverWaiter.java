package selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DriverWaiter {

    public static void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getInstance(), Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getInstance(), Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getInstance(), Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}