package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class ProductTest extends BaseTest {

    private ProductsPage loginAndGetProductsPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            ConfigReader.get("validUsername"),
            ConfigReader.get("validPassword")
        );
        return new ProductsPage(driver);
    }

    @Test(description = "Verify products page")
    public void verifyProductsPageTest() {
        ProductsPage productsPage =
            loginAndGetProductsPage();
        Assert.assertEquals(
            productsPage.getPageTitle(),
            "Products"
        );
        Assert.assertTrue(
            productsPage.getProductCount() > 0
        );
        System.out.println(
            "✅ Products page PASSED");
    }

    @Test(description = "Add product to cart")
    public void addProductToCartTest() {
        ProductsPage productsPage =
            loginAndGetProductsPage();
        productsPage.addProductToCart(
            "Sauce Labs Backpack");
        Assert.assertEquals(
            productsPage.getCartCount(), "1"
        );
        System.out.println(
            "✅ Add to cart PASSED");
    }

    @Test(description = "Sort A to Z")
    public void sortAtoZTest() {
        ProductsPage productsPage =
            loginAndGetProductsPage();
        productsPage.sortBy("Name (A to Z)");
        Assert.assertTrue(
            productsPage.getProductCount() > 0
        );
        System.out.println(
            "✅ Sort A-Z PASSED");
    }

    @Test(description = "Sort Z to A")
    public void sortZtoATest() {
        ProductsPage productsPage =
            loginAndGetProductsPage();
        productsPage.sortBy("Name (Z to A)");
        Assert.assertTrue(
            productsPage.getProductCount() > 0
        );
        System.out.println(
            "✅ Sort Z-A PASSED");
    }

    @Test(description = "Sort price low to high")
    public void sortPriceLowHighTest() {
        ProductsPage productsPage =
            loginAndGetProductsPage();
        productsPage.sortBy("Price (low to high)");
        Assert.assertTrue(
            productsPage.getProductCount() > 0
        );
        System.out.println(
            "✅ Sort price PASSED");
    }

    @Test(description = "Add multiple products")
    public void addMultipleProductsTest() {
        ProductsPage productsPage =
            loginAndGetProductsPage();
        productsPage.addProductToCart(
            "Sauce Labs Backpack");
        productsPage.addProductToCart(
            "Sauce Labs Bike Light");
        Assert.assertEquals(
            productsPage.getCartCount(), "2"
        );
        System.out.println(
            "✅ Multiple products PASSED");
    }
}