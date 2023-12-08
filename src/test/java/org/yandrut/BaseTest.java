package org.yandrut;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import selenium.DriverProvider;

public class BaseTest {

    @BeforeMethod
    public void openBrowser() {
        WebDriver driver = DriverProvider.getInstance();
        driver.get("https://demowebshop.tricentis.com/");
    }

    @AfterMethod
    public void quitBrowser() {
        DriverProvider.quit();
    }
}
