package org.yandrut;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import org.yandrut.data.UserData;
import org.yandrut.pages.RandomEmailGeneratorPage;
import org.yandrut.service.DataReader;
import org.yandrut.service.UserCreator;
import org.yandrut.drivers.DriverProvider;
import org.yandrut.pages.HomePage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoWebTest extends BaseTest {

    public UserData userData = UserCreator.createUser();

    public String getData(String key) {
        return DataReader.getTestData(key);
    }

    @Test
    void allowsToRegisterUser() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        RandomEmailGeneratorPage generator = new RandomEmailGeneratorPage(DriverProvider.getInstance());

        String generatedEmail = page.clickOnRegisterButton()
                .chooseGender(getData("test.data.gender"))
                .setFirstNameAndLastName(getData("test.data.firstName"), getData("test.data.lastName"))
                .openNewTab()
                .switchToWindowHandleAtIndex(1, "Switches to new tab")
                .openURL(getData("urls.emailService"))
                .getTemporaryEmailValue();

        userData.setEmail(generatedEmail);

        boolean isUserRegistered = generator.switchToWindowHandleAtIndex(0, "Switches back to original tab")
                .setEmail(userData.getEmail())
                .setAndConfirmPassword(getData("user.password"))
                .submitRegistering()
                .isUserRegistered();
        assertTrue(isUserRegistered);
    }

    @Test()
    void allowsLoginUser() {
        HomePage page = new HomePage(DriverProvider.getInstance());

        boolean isUserLoggedIn = page.pressLoginButton()
                .userLogin(userData.getEmail(), userData.getPassword())
                .submitLogin()
                .isUserLoggedIn();
        assertTrue(isUserLoggedIn);
    }

    @Test
    void computerGroupHas3SubGroups() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        page.hoverOverComputersSection();
        String subgroups = getData("test.data.subgroups");

        List<String> expected = Arrays.stream(subgroups.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        List <String> actual = page.getComputersListTextValues(page.getComputersList());
        assertEquals(expected, actual);
    }

    @Test
    void allowsSortingItems() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        String sortBy = getData("test.data.sortBy");

        boolean itemsSorted = page.hoverOverComputersSection()
                .switchToDesktopsSection()
                .clickOnSortingOptions()
                .selectSortingOption(sortBy)
                .areItemsSorted(sortBy, page.getProductValues(getData("test.data.nameOrPrices")), getData("test.data.additionalInfo"));
        assertTrue(itemsSorted);
    }

    @Test
    void allowsChangingNumberOfItemsOnPage() {
        // not done
        HomePage page = new HomePage(DriverProvider.getInstance());
        List<WebElement> elementsPerPageOptions = page.hoverOverComputersSection()
                .switchToDesktopsSection()
                .clickOnDisplayOptionsSelector()
                .getDisplayOptions();

        page.setNumberOfItemsOnPage(4, elementsPerPageOptions);
        boolean numberOfElementsMatches = page.numberOfItemsOnPageMatches(4, page.getProductValues("names"));
        assertTrue(numberOfElementsMatches);
    }

    @Test
    void allowsAddingItemsToWishlist() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        boolean successfulAction = page.moveToApparelAndShoesSection()
            .clickOnProduct(getData("test.data.productName"))
            .addProductToWishlist()
            .isActionSuccessful();
        assertTrue(successfulAction);
    }

    @Test
    void allowsAddingItemsToCard() {
        HomePage page = new HomePage(DriverProvider.getInstance());
        boolean successfulAction = page.moveToApparelAndShoesSection()
                .clickOnProduct("test.data.productName")
                .addProductToCart()
                .isActionSuccessful();
        assertTrue(successfulAction);
    }

    @Test
    void allowsRemovingItemsFromCard() {
        HomePage page = new HomePage(DriverProvider.getInstance());

        boolean isCardEmpty = page.moveToApparelAndShoesSection()
        .clickOnProduct(getData("test.data.productName"))
        .addProductToCart()
        .moveToCart()
        .removeProductFromCart()
        .clickOnUpdateShoppingCart()
        .isCardEmpty();
        assertTrue(isCardEmpty);
    }

    @Test
    void allowsCheckoutItems() {
        HomePage page = new HomePage(DriverProvider.getInstance());

        String actual = page.moveToApparelAndShoesSection()
        .clickOnProduct("50's Rockabilly Polka Dot Top JR Plus Size")
        .addProductToCart()
        .moveToCart()
        .agreeWithTermsOfService()
        .clickOnCheckoutButton()
                .getTitle();

        String expected = "Demo Web Shop. Login";
        assertEquals(expected, actual);
    }
}