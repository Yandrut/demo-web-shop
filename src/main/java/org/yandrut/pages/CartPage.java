package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    @FindBy(xpath = "//input[@name='removefromcart']")
    private WebElement removeFromCart;
    @FindBy(xpath = "//input[@class='button-2 update-cart-button']")
    private WebElement updateShoppingCart;
    @FindBy(css = "span.cart-qty")
    private WebElement cartQuantity;
    @FindBy(id = "termsofservice")
    private WebElement termsOfService;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    private final WebDriver driver;
    Logger logger = LogManager.getLogger(CartPage.class);

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage removeProductFromCart() {
        logger.info("Remove product from cart");
        removeFromCart.click();
        return this;
    }

    public CartPage clickOnUpdateShoppingCart() {
        logger.info("Click on update shopping cart");
        updateShoppingCart.click();
        return this;
    }

    public boolean isCardEmpty() {
        logger.info("Is card empty");
        String cartQuantity = this.cartQuantity.getText();
        return (cartQuantity.equals("(0)"));
    }

    public CartPage agreeWithTermsOfService() {
        logger.info("Agree with terms of service");
        termsOfService.click();
        return this;
    }

    public CartPage clickOnCheckoutButton() {
        logger.info("Click on checkout button");
        checkoutButton.click();
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
