package selenium;

import org.openqa.selenium.WebDriver;
import java.util.Objects;

public class DriverProvider {
    private static WebDriver driver;

    private DriverProvider() {}

    public static WebDriver getInstance() {
        if (Objects.isNull(driver)) {
            driver = DriverFactory.getDriver("chrome");
        }
        return driver;
    }

    public static void quit() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }
}
