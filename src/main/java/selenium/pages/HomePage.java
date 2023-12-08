package selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;


public class HomePage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    By REGISTER_BUTTON = By.xpath("//a[@class='ico-register']");
    By MALE_RADIO_BUTTON = By.xpath("//input[@id='gender-male']");
    By FEMALE_RADIO_BUTTON = By.xpath("//input[@id='gender-female']");
    By FIRST_NAME_INPUT = By.id("FirstName");
    By LAST_NAME_INPUT = By.id("LastName");
    By EMAIL_INPUT = By.id("Email");
    By PASSWORD_INPUT = By.id("Password");
    By CONFIRM_PASSWORD_INPUT = By.id("ConfirmPassword");
    By SUBMIT_REGISTER = By.id("register-button");
    By SUCCESSFUL_REGISTER = By.xpath("//input[@class='button-1 register-continue-button']");
    By LOGIN_BUTTON = By.xpath("//a[@class='ico-login']");
    By SUBMIT_LOGIN = By.xpath("//input[@class='button-1 login-button']");
    By ACCOUNT_BUTTON = By.xpath("//a[@class='account']");
    By COMPUTERS_LIST_HOVER = By.xpath("//a[@href='/computers']");

    public HomePage (WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnRegisterButton() {
        logger.info("Click on register button");
        driver.findElement(REGISTER_BUTTON).click();
    }

    public void chooseGender(String maleOrFemale) {
        logger.info("Choose gender: " + maleOrFemale);
        if (maleOrFemale.equalsIgnoreCase("Male")) {
            driver.findElement(MALE_RADIO_BUTTON).click();
        } else if (maleOrFemale.equalsIgnoreCase("Female")) {
            driver.findElement(FEMALE_RADIO_BUTTON).click();
        } else {
            throw new RuntimeException("Invalid gender");
        }
    }

    public void setFirstNameAndLastName(String firstName, String lastName) {
        logger.info("Set first name and last name: " + firstName + ", " + lastName);
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    }

    public void openNewTab() {
        logger.info("Open new tab");
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void switchToWindowHandleAtIndex(int index, String additionalInfo) {
        logger.info("Switch to window handle at index: " + index + " : " + additionalInfo);
        Set<String> windowHandles = driver.getWindowHandles();
        driver.switchTo().window((String) windowHandles.toArray()[index]);

    }

    public void setEmail(String email) {
        logger.info("Set email: " + email);
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void setAndConfirmPassword(String password) {
        logger.info("Set and confirm password: " + password);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(CONFIRM_PASSWORD_INPUT).sendKeys(password);
    }

    public void submitRegistering() {
        logger.info("Submit registering");
        driver.findElement(SUBMIT_REGISTER).click();
    }

    public boolean isUserRegistered() {
        logger.info("Is user registered");
        return driver.findElement(SUCCESSFUL_REGISTER).isDisplayed();
    }

    public void pressLoginButton() {
        logger.info("Press login button");
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void userLogin(String email, String password) {
        logger.info("User login: " + email + ", " + password);
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void submitLogin() {
        logger.info("Submit login");
        driver.findElement(SUBMIT_LOGIN).click();
    }

    public boolean isUserLoggedIn() {
        logger.info("Is user logged in");
        return driver.findElement(ACCOUNT_BUTTON).isDisplayed();
    }

    public void getComputersList() {
        logger.info("Get computers list");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(COMPUTERS_LIST_HOVER));
    }
}