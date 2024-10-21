package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    @FindBy(id = "Email")
    private WebElement emailInput;
    @FindBy(id = "Password")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@class='button-1 login-button']")
    private WebElement submitLogin;
    @FindBy(xpath = "//a[@class='account']")
    private WebElement accountButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public LoginPage userLogin(String email, String password) {
        logger.info("User login: {} , {}", email, password);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage submitLogin() {
        logger.info("Submit login");
        submitLogin.click();
        return this;
    }

    public boolean isUserLoggedIn() {
        logger.info("Is user logged in");
        return accountButton.isDisplayed();
    }
}
