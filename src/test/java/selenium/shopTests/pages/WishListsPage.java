package selenium.shopTests.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class WishListsPage extends AccountHeaderPage {

    final String wishlistXpath = "//*[@id='block-history']//tr/td[1]//a";
    final String wishlistNameXpath = "//*[@id='block-history']//tr//a[contains(text(),'%s')]";

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "submitWishlist")
    WebElement saveBtn;

    @FindBy(xpath = wishlistXpath)
    List<WebElement> wishlists;

    @FindBy(css = ".wishlist_delete a")
    WebElement deleteWishlistButton;

    @FindBy(xpath = "//*[@id='block-history']//tr/td[5]//a")
    WebElement viewWishlistButton;

    @FindBy(css = ".wlp_bought ul li img")
    List<WebElement> wishlistProducts;

    public void createWishlist(String name) {
        nameField.sendKeys(name);
        saveBtn.click();
    }

    public void deleteWishlist() {
        deleteWishlistButton.click();
        acceptAlert();
    }

    public boolean isWishlistExist(String name) {
        WebElement wishlist = driver.findElement(By.xpath(String.format(wishlistNameXpath, name)));

        return wishlist != null;
    }

    public boolean isWishlistEmpty() {
        return wishlists.size() == 0;
    }

    public int getWishlistsCount () {
        return wishlists.size();
    }

    public List<String> getWishlistProducts () {
        List<String> productNames = new ArrayList<>();

        viewWishlistButton.click();

        for (WebElement product: wishlistProducts) {
            productNames.add(product.getAttribute("alt"));
        }
        return productNames;
    }

    private void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
