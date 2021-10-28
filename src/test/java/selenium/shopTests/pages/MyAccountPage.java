package selenium.shopTests.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends AccountHeaderPage{

    @FindBy(css = ".lnk_wishlist span")
    WebElement myWishListsBtn;

    public void openWishlists(){
        myWishListsBtn.click();
    }

    public boolean isLoggedIn(){
        try {
            return accountName.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
