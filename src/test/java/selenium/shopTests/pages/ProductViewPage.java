package selenium.shopTests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductViewPage extends AccountHeaderPage {

    @FindBy(css = "#wishlist_button")
    WebElement addToWishlistBtn;

    @FindBy(css = ".fancybox-skin a")
    WebElement closeConfirmationMessageBtn;

    @FindBy(id = "add_to_cart")
    WebElement addToCartBtn;

    @FindBy(xpath = "//*[@class = 'continue btn btn-default button exclusive-medium']")
    WebElement continuePopupButton;

    public void addProductToWishlist() {
        addToWishlistBtn.click();
        closeConfirmationMessageBtn.click();
    }

    public void addProductToCart() {
        addToCartBtn.click();
        continuePopupButton.click();
    }
}
