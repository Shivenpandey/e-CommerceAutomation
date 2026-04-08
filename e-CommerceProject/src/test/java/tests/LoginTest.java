package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

	@Test
	public void validLoginTest() {
		// Enter username
		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		// Enter password
		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		// Click login button
		driver.findElement(By.id("login-button")).click();

		// Verify redirected to inventory page
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed!");
		System.out.println("✅ Valid login test PASSED");
	}

	@Test
	public void invalidLoginTest() {
		driver.findElement(By.id("user-name")).sendKeys("wrong_user");

		driver.findElement(By.id("password")).sendKeys("wrong_pass");

		driver.findElement(By.id("login-button")).click();

		// Verify error message appears
		String errorMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMsg.contains("Username and password do not match"), "Error message not shown!");
		System.out.println("✅ Invalid login test PASSED");
	}

	@Test
	public void emptyFieldsTest() {
		// Click login without entering anything
		driver.findElement(By.id("login-button")).click();

		String errorMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMsg.contains("Username is required"), "Empty fields error not shown!");
		System.out.println("✅ Empty fields test PASSED");
	}

	@Test
	public void lockedUserTest() {
		driver.findElement(By.id("user-name")).sendKeys("locked_out_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		String errorMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMsg.contains("Sorry, this user has been locked out"), "Locked user error not shown!");
		System.out.println("✅ Locked user test PASSED");
	}
}