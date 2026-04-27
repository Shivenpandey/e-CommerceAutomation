package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(css = "[data-test='continue-shopping']")
    private WebElement continueShoppingButton;

    @FindBy(css = ".cart_item .inventory_item_name")
    private List<WebElement> cartItemNames;

    @FindBy(css = ".btn_secondary.cart_button")
    private List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void clickCheckout() {
        click(checkoutButton);
    }

    public void clickContinueShopping() {
        click(continueShoppingButton);
    }

    public boolean isItemInCart(String productName) {
        for (WebElement item : cartItemNames) {
            if (item.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String productName) {
        for (int i = 0; i < cartItemNames.size(); i++) {
            if (cartItemNames.get(i).getText()
                    .equals(productName)) {
                click(removeButtons.get(i));
                break;
            }
        }
    }

    public void goToCart() {
        driver.findElement(
            org.openqa.selenium.By
                .className("shopping_cart_link")).click();
    }
}