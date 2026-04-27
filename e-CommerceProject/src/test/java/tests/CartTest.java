package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class CartTest extends BaseTest {

    private CartPage loginAddAndGoToCart(
            String productName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            ConfigReader.get("validUsername"),
            ConfigReader.get("validPassword")
        );
        ProductsPage productsPage =
            new ProductsPage(driver);
        productsPage.addProductToCart(productName);
        productsPage.clickCart();
        return new CartPage(driver);
    }

    @Test(description = "Verify item in cart")
    public void verifyItemInCartTest() {
        CartPage cartPage = loginAddAndGoToCart(
            "Sauce Labs Backpack");
        Assert.assertTrue(
            cartPage.isItemInCart(
                "Sauce Labs Backpack")
        );
        System.out.println(
            "✅ Item in cart PASSED");
    }

    @Test(description = "Remove item from cart")
    public void removeItemFromCartTest() {
        CartPage cartPage = loginAddAndGoToCart(
            "Sauce Labs Backpack");
        cartPage.removeItem("Sauce Labs Backpack");
        Assert.assertEquals(
            cartPage.getCartItemCount(), 0
        );
        System.out.println(
            "✅ Remove item PASSED");
    }

    @Test(description = "Verify cart count")
    public void verifyCartCountTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            ConfigReader.get("validUsername"),
            ConfigReader.get("validPassword")
        );
        ProductsPage productsPage =
            new ProductsPage(driver);
        productsPage.addProductToCart(
            "Sauce Labs Backpack");
        productsPage.addProductToCart(
            "Sauce Labs Bike Light");
        productsPage.clickCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(
            cartPage.getCartItemCount(), 2
        );
        System.out.println(
            "✅ Cart count PASSED");
    }

    @Test(description = "Continue shopping")
    public void continueShoppingTest() {
        CartPage cartPage = loginAddAndGoToCart(
            "Sauce Labs Backpack");
        cartPage.clickContinueShopping();
        Assert.assertTrue(
            driver.getCurrentUrl()
                .contains("inventory")
        );
        System.out.println(
            "✅ Continue shopping PASSED");
    }
}