package org.yandrut;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.yandrut.drivers.DriverProvider;

public class BaseTest {

    @BeforeEach
    public void openBrowser() {
        WebDriver driver = DriverProvider.getInstance();
        driver.get("https://demowebshop.tricentis.com/");
    }

    @AfterEach
    public void quitBrowser() {
        DriverProvider.quit();
    }
}
