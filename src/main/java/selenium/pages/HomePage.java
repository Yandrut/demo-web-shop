package selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class HomePage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HomePage.class);

   private final By REGISTER_BUTTON = By.xpath("//a[@class='ico-register']");
   private final By MALE_RADIO_BUTTON = By.xpath("//input[@id='gender-male']");
   private final By FEMALE_RADIO_BUTTON = By.xpath("//input[@id='gender-female']");
   private final By FIRST_NAME_INPUT = By.id("FirstName");
   private final By LAST_NAME_INPUT = By.id("LastName");
   private final By EMAIL_INPUT = By.id("Email");
   private final By PASSWORD_INPUT = By.id("Password");
   private final By CONFIRM_PASSWORD_INPUT = By.id("ConfirmPassword");
   private final By SUBMIT_REGISTER = By.id("register-button");
   private final By SUCCESSFUL_REGISTER = By.xpath("//input[@class='button-1 register-continue-button']");
   private final By LOGIN_BUTTON = By.xpath("//a[@class='ico-login']");
   private final By SUBMIT_LOGIN = By.xpath("//input[@class='button-1 login-button']");
   private final By ACCOUNT_BUTTON = By.xpath("//a[@class='account']");
   private final By COMPUTERS_LIST_HOVER = By.xpath("//div[@class='header-menu']//a[@href='/computers']");
   private final By COMPUTERS_LIST = By.xpath("//ul[@class='sublist firstLevel active']/li/a");
   private final By DESKTOPS_SECTION = By.xpath("//ul/li/a[@href='/desktops']");
   private final By SORTING_OPTIONS = By.id("products-orderby");
   private final By SORT_BY_LOW_TO_HIGH = By.xpath("//option[text()='Price: Low to High']");
   private final By SORT_FROM_HIGH_TO_LOW = By.xpath("//option[text()='Price: High to Low']");
   private final By SORT_FROM_A_TO_Z = By.xpath("//option[text()='Name: A to Z']");
   private final By PRODUCT_PRICES_LIST = By.xpath("//span[@class='price actual-price']");
   private final By PRODUCT_NAMES_LIST = By.xpath("//div/h2/a");
   private final By PRODUCT_QUANTITY_SELECTOR = By.id("products-pagesize");
   private final By ELEMENTS_PER_PAGE_OPTIONS = By.xpath("//option[contains(@value, 'pagesize')]");



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

    public void hoverOverComputersSection() {
        logger.info("Hover over computers sections");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(COMPUTERS_LIST_HOVER)).perform();
    }

    public List<WebElement> getComputersList() {
        logger.info("Get computers list");
        return driver.findElements(COMPUTERS_LIST);
    }

    public List<String> getComputersListTextValues(List<WebElement> computersList) {
        logger.info("Get computers list text values");
        return computersList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void switchToDesktopsSection() {
        logger.info("Switch to desktops section");
        driver.findElement(DESKTOPS_SECTION).click();
    }

    public void clickOnSortingOptions() {
        logger.info("Click on sorting options");
        driver.findElement(SORTING_OPTIONS).click();
    }

    public void selectSortingOption(String sortBy) {
        logger.info("Select sorting option");
        switch (sortBy) {
            case "Price: Low to High" :
                driver.findElement(SORT_BY_LOW_TO_HIGH).click();
                break;
            case "Name: A to Z" :
                driver.findElement(SORT_FROM_A_TO_Z).click();
                break;
            case "Price: High to Low" :
                driver.findElement(SORT_FROM_HIGH_TO_LOW).click();
                break;

                default: throw new RuntimeException("Invalid sorting option");
        }
    }
    public List<String> getProductValues(String pricesOrNames) {
        logger.info("Get product values: " + pricesOrNames);
        if (pricesOrNames.equalsIgnoreCase("prices")) {
            return driver.findElements(PRODUCT_PRICES_LIST)
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }

        else if (pricesOrNames.equalsIgnoreCase("names")) {
            return driver.findElements(PRODUCT_NAMES_LIST)
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("Invalid list value chosen");
    }

    @SuppressWarnings("ReassignedVariable")
    public boolean areItemsSorted(String sortBy, List<String> productValues, String additionalInfo) {
        logger.info("Are items sorted" + ": " + additionalInfo);
        int flag;
        switch (sortBy) {
            case "Price: Low to High" :
                flag = 0;
                 // counts if adjacent elements are sorted
               for (int i = 0; i < productValues.size() -1; i++) {
                   double first = Double.parseDouble(productValues.get(i));
                   double second = Double.parseDouble(productValues.get(i+1));

                   if (first <= second) {
                       flag++;
                   }
               }
                return flag == productValues.size() -1;

            case "Name: A to Z" :
                flag = 0; // counts if adjacent elements are sorted
                for (int i = 0; i < productValues.size() -1; i++) {
                    String first = productValues.get(i);
                    String second = productValues.get(i+1);
                    if (first.charAt(0) <= second.charAt(0)) {
                        flag++;
                    }
                }
                return flag == productValues.size() -1;

            case "Price: High to Low" :
                flag = 0; // counts if adjacent elements are sorted
                for (int i = 0; i < productValues.size() -1; i++) {
                    double first = Double.parseDouble(productValues.get(i));
                    double second = Double.parseDouble(productValues.get(i+1));
                        if (first >= second) {
                            flag++;
                        }
                }
                return flag == productValues.size() -1;

            default: throw new RuntimeException("Invalid sorting option");
        }
    }

    public void clickOnDisplayElementsPerPage() {
        logger.info("Click on display elements per page");
        driver.findElement(PRODUCT_QUANTITY_SELECTOR).click();
    }

    public List<WebElement> getElementsPerPageOptions() {
        logger.info("Get elements per page options");
        return driver.findElements(ELEMENTS_PER_PAGE_OPTIONS);
    }

    public void setNumberOfItemsOnPage(String elementsPerPage, List<WebElement> elementsPerPageOptions) {
        logger.info("Set number of items on page: " + elementsPerPage);
        for (WebElement option : elementsPerPageOptions) {
            if (option.getText().equals(elementsPerPage)) {
                option.click();
            } else {
                throw new RuntimeException("Invalid number of elements");
            }
        }
    }

    public boolean numberOfElementsOnPageMatches(int elementsPerPage, List<String> elements) {
        return elementsPerPage == elements.size();
    }
}