package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(description = "Valid login test")
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            ConfigReader.get("validUsername"),
            ConfigReader.get("validPassword")
        );
        Assert.assertTrue(
            driver.getCurrentUrl()
                .contains("inventory"),
            "Login failed!"
        );
        System.out.println(
            "✅ Valid login PASSED");
    }

    @Test(description = "Invalid login test")
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            "wrong_user", "wrong_pass");
        Assert.assertTrue(
            loginPage.isErrorDisplayed(),
            "Error not shown!"
        );
        Assert.assertTrue(
            loginPage.getErrorMessage()
                .contains(
                "Username and password do not match")
        );
        System.out.println(
            "✅ Invalid login PASSED");
    }

    @Test(description = "Empty fields test")
    public void emptyFieldsTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        Assert.assertTrue(
            loginPage.getErrorMessage()
                .contains("Username is required")
        );
        System.out.println(
            "✅ Empty fields PASSED");
    }

    @Test(description = "Locked user test")
    public void lockedUserTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            ConfigReader.get("lockedUsername"),
            ConfigReader.get("validPassword")
        );
        Assert.assertTrue(
            loginPage.getErrorMessage()
                .contains("locked out")
        );
        System.out.println(
            "✅ Locked user PASSED");
    }
}