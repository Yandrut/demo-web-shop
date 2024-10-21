package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ApparelShoesPage {
    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ApparelShoesPage.class);

    @FindBy(xpath = "//div/h2/a")
    private List<WebElement> productsList;
    @FindBy(id = "add-to-wishlist-button-5")
    private WebElement addToWishlistButton;
    @FindBy(xpath = "//div[@class='bar-notification success']")
    private WebElement successfulActionBanner;
    @FindBy(id = "add-to-cart-button-5")
    private WebElement addToCartButton;
    @FindBy(xpath = "//a[@class='ico-cart']")
    private WebElement cartSection;


    public ApparelShoesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ApparelShoesPage clickOnProduct(String productName) {
        logger.info("Click on product");

        for (WebElement product : productsList) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                return this;
            }
        }
        throw new RuntimeException("Invalid product name");
    }

    public ApparelShoesPage addProductToWishlist() {
        logger.info("Add product to wishlist");
        addToWishlistButton.click();
        return this;
    }

    public boolean isActionSuccessful() {
        logger.info("Is action successful");
        String styleAttributeValue = successfulActionBanner.getAttribute("style");
        return !(styleAttributeValue.equals("display: none;"));
    }

    public ApparelShoesPage addProductToCart() {
        logger.info("Add product to cart");
        addToCartButton.click();
        return this;
    }

    public CartPage moveToCart() {
        logger.info("Move to cart");
        cartSection.click();
        return new CartPage(driver);
    }
}