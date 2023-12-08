package org.yandrut;

import org.testng.annotations.Test;
import selenium.DriverProvider;
import selenium.pages.HomePage;
import selenium.RandomEmailGenerator;

import static org.junit.Assert.assertTrue;

public class DemoWebTesting extends BaseTest {

    // User object created for saving password and email values
    public User user = new User();

    @Test
    public void allowsToRegisterUser() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        RandomEmailGenerator generator = new RandomEmailGenerator(DriverProvider.getInstance());
        page.clickOnRegisterButton();
        // Inputting user information
        page.chooseGender("Male");
        page.setFirstNameAndLastName("Vasyl", "Vasylovych");
        page.openNewTab();
        page.switchToWindowHandleAtIndex(1, "Switches to new tab");
        // Using temporary e-mail for registering
        generator.openURL("https://10minutemail.net");
        user.setEmail(generator.getTemporaryEmailValue());
        user.setPassword("qwertyuiop[]1488");
        page.switchToWindowHandleAtIndex(0, "Switches back to original tab");
        page.setEmail(user.getEmail());
        page.setAndConfirmPassword(user.getPassword());
        page.submitRegistering();
        boolean isUserRegistered = page.isUserRegistered();
        assertTrue(isUserRegistered);
    }

    @Test(dependsOnMethods = "allowsToRegisterUser")
    public void allowsLoginUser() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        page.pressLoginButton();
        page.userLogin(user.getEmail(), user.getPassword());
        page.submitLogin();
        boolean isUserLoggedIn = page.isUserLoggedIn();
        assertTrue(isUserLoggedIn);
    }

    @Test
    public void computerGroupHas3SubGroups() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        page.getComputersList();
    }

    @Test
    public void allowsSortingItems() {

    }

    @Test
    public void allowsChangingNumberOfItemsOnPage() {

    }

    @Test
    public void allowsAddingItemsToWishlist() {

    }

    @Test
    public void allowsAddingItemsToCard() {

    }

    @Test
    public void allowsRemovingItemsFromCard() {

    }

    @Test
    public void allowsCheckoutItems() {

    }
}