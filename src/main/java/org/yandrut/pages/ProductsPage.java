package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    private static final Logger logger = LogManager.getLogger(ProductsPage.class);

    @FindBy(xpath = "//ul/li/a[@href='/desktops']")
    private WebElement desktopsSection;
    @FindBy(id = "products-orderby")
    private WebElement sortingOptions;
    @FindBy(xpath = "//option[text()='Price: Low to High']")
    private WebElement sortByLowToHigh;
    @FindBy(xpath = "//option[text()='Price: High to Low']")
    private WebElement sortFromHighToLow;
    @FindBy(xpath = "//option[text()='Name: A to Z']")
    private WebElement sortFromAToZ;
    @FindBy(id = "products-pagesize")
    private WebElement displayOptionsSelector;
    @FindBy(xpath = "//option[contains(@value, 'pagesize')]")
    private List<WebElement> displayOptions;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductsPage switchToDesktopsSection() {
        logger.info("Switch to desktops section");
        desktopsSection.click();
        return this;
    }

    public ProductsPage clickOnSortingOptions() {
        logger.info("Click on sorting options");
        sortingOptions.click();
        return this;
    }

    public ProductsPage selectSortingOption(String sortBy) {
        logger.info("Select sorting option");
        switch (sortBy) {
            case "Price: Low to High" :
                sortByLowToHigh.click();
                break;
            case "Name: A to Z" :
                sortFromAToZ.click();
                break;
            case "Price: High to Low" :
                sortFromHighToLow.click();
                break;

            default: throw new RuntimeException("Invalid sorting option");
        }
        return this;
    }

    public boolean areItemsSorted(String sortBy, List<String> productValues, String additionalInfo) {
        logger.info("Are items sorted" + ": {}", additionalInfo);
        int flag;
        switch (sortBy) {
            case "Price: Low to High" :
                flag = 0; // counts if adjacent elements are sorted
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

    public ProductsPage clickOnDisplayOptionsSelector() {
        logger.info("Click on display options selector");
        displayOptionsSelector.click();
        return this;
    }

    public List<WebElement> getDisplayOptions() {
        logger.info("Get display options");
        return displayOptions;
    }
}
