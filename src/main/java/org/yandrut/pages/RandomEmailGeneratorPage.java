package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

import static org.yandrut.drivers.DriverWaiter.*;

public class RandomEmailGeneratorPage {

    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(RandomEmailGeneratorPage.class);

    @FindBy(xpath = "//input[@class='mailtext']")
    WebElement mailText;

    public RandomEmailGeneratorPage(WebDriver driver) {
        this.driver = driver;
    }

    public RandomEmailGeneratorPage openURL(String urlToEmailService) {
        logger.info("Open URL: {}", urlToEmailService);
        driver.get(urlToEmailService);
        return this;
    }

    public String getTemporaryEmailValue() {
        logger.info("Get temporary email value");
        waitForElementToBeVisible(mailText);
        return mailText.getAttribute("value");
    }

    public HomePage switchToWindowHandleAtIndex(int index, String additionalInfo) {
        logger.info("Switch to window handle at index: {} , {}" ,index, additionalInfo);
        Set<String> windowHandles = driver.getWindowHandles();
        driver.switchTo().window((String) windowHandles.toArray()[index]);
        return new HomePage(driver);
    }
}