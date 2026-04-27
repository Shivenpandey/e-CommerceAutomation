package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartCount;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".btn_inventory")
    private List<WebElement> addToCartButtons;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public int getProductCount() {
        return productNames.size();
    }

    public String getCartCount() {
        try {
            return cartCount.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void sortBy(String option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    public void clickCart() {
        click(cartIcon);
    }

    public void addProductToCart(String productName) {
        List<WebElement> names = productNames;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText()
                    .equals(productName)) {
                click(addToCartButtons.get(i));
                break;
            }
        }
    }
}