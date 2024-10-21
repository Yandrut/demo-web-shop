package org.yandrut.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DriverFactory {

    private DriverFactory() {}

    public static WebDriver getDriver(String driverName) {
        WebDriver driver;

        if (driverName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                String downloadPath = "/home/digital/Завантаження";
                options.addArguments("download.default_directory=" + downloadPath);

                driver = new ChromeDriver(options);
        } else if (driverName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (driverName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid name of the driver");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}