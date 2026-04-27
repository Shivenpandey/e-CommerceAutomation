package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class CheckoutTest extends BaseTest {

    private CheckoutPage reachCheckoutPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            ConfigReader.get("validUsername"),
            ConfigReader.get("validPassword")
        );
        ProductsPage productsPage =
            new ProductsPage(driver);
        productsPage.addProductToCart(
            "Sauce Labs Backpack");
        productsPage.clickCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        return new CheckoutPage(driver);
    }

    @Test(description = "Complete E2E checkout")
    public void completeCheckoutTest() {
        CheckoutPage checkoutPage =
            reachCheckoutPage();
        checkoutPage.fillCheckoutForm(
            "John", "Doe", "12345");
        checkoutPage.clickFinish();
        Assert.assertTrue(
            checkoutPage.isOrderConfirmed()
        );
        System.out.println(
            "✅ Complete checkout PASSED");
    }

    @Test(description = "Empty first name")
    public void emptyFirstNameTest() {
        CheckoutPage checkoutPage =
            reachCheckoutPage();
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        Assert.assertTrue(
            checkoutPage.getErrorMessage()
                .contains("First Name is required")
        );
        System.out.println(
            "✅ Empty first name PASSED");
    }

    @Test(description = "Empty last name")
    public void emptyLastNameTest() {
        CheckoutPage checkoutPage =
            reachCheckoutPage();
        checkoutPage.enterFirstName("John");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        Assert.assertTrue(
            checkoutPage.getErrorMessage()
                .contains("Last Name is required")
        );
        System.out.println(
            "✅ Empty last name PASSED");
    }

    @Test(description = "Empty postal code")
    public void emptyPostalCodeTest() {
        CheckoutPage checkoutPage =
            reachCheckoutPage();
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.clickContinue();
        Assert.assertTrue(
            checkoutPage.getErrorMessage()
                .contains("Postal Code is required")
        );
        System.out.println(
            "✅ Empty postal code PASSED");
    }

    @Test(description = "Verify order summary")
    public void verifyOrderSummaryTest() {
        CheckoutPage checkoutPage =
            reachCheckoutPage();
        checkoutPage.fillCheckoutForm(
            "John", "Doe", "12345");
        Assert.assertTrue(
            driver.getCurrentUrl()
                .contains("checkout-step-two")
        );
        System.out.println(
            "✅ Order summary PASSED");
    }
}