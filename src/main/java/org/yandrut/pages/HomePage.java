package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HomePage {
    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HomePage.class);

   @FindBy(xpath = "//a[@class='ico-register']")
   private WebElement registerButton;
   @FindBy(xpath = "//input[@id='gender-male']")
   private WebElement maleRadioButton;
   @FindBy (xpath = "//input[@id='gender-female']")
   private WebElement femaleRadioButton;
   @FindBy(id = "FirstName")
   private WebElement firstNameInput;
   @FindBy(id = "LastName")
   private WebElement lastNameInput;
   @FindBy(id = "ConfirmPassword") private WebElement confirmPasswordInput;
   @FindBy(id = "register-button")
   private WebElement submitRegister;
   @FindBy(xpath = "//input[@class='button-1 register-continue-button']")
   private WebElement successfulRegister;
   @FindBy(xpath = "//a[@class='ico-login']")
   private WebElement loginButton;
   @FindBy(xpath = "//div[@class='header-menu']//a[@href='/computers']")
   private WebElement computersListHover;
   @FindBy(xpath = "//ul[@class='sublist firstLevel active']/li/a")
   private List<WebElement> computersList;
    @FindBy(xpath = "//span[@class='price actual-price']")
    private List<WebElement> productPricesList;
    @FindBy(xpath = "//div/h2/a")
    private List<WebElement> productsList;
    @FindBy(xpath = "//a[@href='/apparel-shoes']")
   private WebElement apparelAndShoesSection;
    @FindBy(id = "Email")
    private WebElement emailInput;
    @FindBy(id = "Password")
    private WebElement passwordInput;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage clickOnRegisterButton() {
        logger.info("Click on register button");
        registerButton.click();
        return this;
    }

    public HomePage chooseGender(String maleOrFemale) {
        logger.info("Choose gender: {}", maleOrFemale);
        if (maleOrFemale.equalsIgnoreCase("Male")) {
            maleRadioButton.click();
        } else if (maleOrFemale.equalsIgnoreCase("Female")) {
            femaleRadioButton.click();
        } else {
            throw new RuntimeException("Invalid gender");
        }
        return this;
    }

    public HomePage setFirstNameAndLastName(String firstName, String lastName) {
        logger.info("Set first name and last name: {} , {}", firstName, lastName);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public HomePage openNewTab() {
        logger.info("Open new tab");
        driver.switchTo().newWindow(WindowType.TAB);
        return this;
    }

    public RandomEmailGeneratorPage switchToWindowHandleAtIndex(int index, String additionalInfo) {
        logger.info("Switch to window handle at index: {} , {}" ,index, additionalInfo);
        Set<String> windowHandles = driver.getWindowHandles();
        driver.switchTo().window((String) windowHandles.toArray()[index]);
        return new RandomEmailGeneratorPage(driver);
    }

    public HomePage setEmail(String email) {
        logger.info("Set email: {}", email);
        emailInput.sendKeys(email);
        return this;
    }

    public HomePage setAndConfirmPassword(String password) {
        logger.info("Set and confirm password: {}", password);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public HomePage submitRegistering() {
        logger.info("Submit registering");
        submitRegister.click();
        return this;
    }

    public boolean isUserRegistered() {
        logger.info("Is user registered");
        return successfulRegister.isDisplayed();
    }

    public LoginPage pressLoginButton() {
        logger.info("Press login button");
        loginButton.click();
        return new LoginPage(driver);
    }

    public ProductsPage hoverOverComputersSection() {
        logger.info("Hover over computers sections");
        Actions actions = new Actions(driver);
        actions.moveToElement(computersListHover).perform();
        return new ProductsPage(driver);
    }

    public List<WebElement> getComputersList() {
        logger.info("Get computers list");
        return computersList;
    }

    public List<String> getComputersListTextValues(List<WebElement> computersList) {
        logger.info("Get computers list text values");
        return computersList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getProductValues(String pricesOrNames) {
        logger.info("Get product values: {}", pricesOrNames);
        if (pricesOrNames.equalsIgnoreCase("prices")) {
            return productPricesList
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }

        else if (pricesOrNames.equalsIgnoreCase("names")) {
            return productsList
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("Invalid list value chosen");
    }

    public void setNumberOfItemsOnPage(int elementsPerPage, List<WebElement> displayOptions) {
        logger.info("Set number of items on page: {}", elementsPerPage);

        for (WebElement option : displayOptions) {
            int optionValue = Integer.parseInt(option.getText());
            if (optionValue == elementsPerPage) {
                option.click();
                return;
            } else {
                throw new RuntimeException("Invalid number of elements");
            }
        }
    }

    public boolean numberOfItemsOnPageMatches(int elementsPerPage, List<String> elements) {
        logger.info("Number of items on page matches");
        return elementsPerPage == elements.size();
    }

    public ApparelShoesPage moveToApparelAndShoesSection() {
        logger.info("Move to apparel and shoes section");
        apparelAndShoesSection.click();
        return new ApparelShoesPage(driver);
    }
}