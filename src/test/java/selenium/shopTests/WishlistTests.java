package selenium.shopTests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenium.shopTests.pages.*;

public class WishlistTests extends TestBase {

    final String email = "java1.test@yandex.com";
    final String password = "1qaz2wsx";

    @AfterEach
    public void testCleanup() {
        WishListsPage wishListsPage = new WishListsPage();
        wishListsPage.deleteWishlist();
        wishListsPage.signOut();
    }

    @DisplayName("Verify the ability to add to auto-created Wishlist ")
    @Description("Login, add product to wishlist and verify that wishlist is auto-created")
    @Test
    void wishlistAutoCreationTest() {
        String productName = "Blouse";

        StartPage startPage = new StartPage();
        startPage.invokeSignIn();

        AuthenticationPage authenticationPage = new AuthenticationPage();
        authenticationPage.login(email, password);

        MyAccountPage myAccountPage = new MyAccountPage();

        Assert.assertTrue(myAccountPage.isLoggedIn());

        myAccountPage.openWishlists();
        WishListsPage wishListsPage = new WishListsPage();

        Assert.assertTrue(wishListsPage.isWishlistEmpty());

        wishListsPage.goHomePage();

        HomeProductPage homeProductPage = new HomeProductPage();
        homeProductPage.viewProduct(productName);

        ProductViewPage productViewPage = new ProductViewPage();
        productViewPage.addProductToWishlist();
        productViewPage.goAccountPage();

        myAccountPage.openWishlists();

        Assert.assertFalse(wishListsPage.isWishlistEmpty());
        Assert.assertEquals(1, wishListsPage.getWishlistsCount());
        Assert.assertTrue(wishListsPage.getWishlistProducts().contains(productName));
    }

    @DisplayName("Verify the ability to add to your Wishlist ")
    @Description("Login, create wishlist, add product to wishlist")
    @Test
    void createWishlistAndAddProductTest() {
        String wishlistName = "my wishlist";
        String productName = "Blouse";

        StartPage startPage = new StartPage();
        startPage.invokeSignIn();

        AuthenticationPage authenticationPage = new AuthenticationPage();
        authenticationPage.login(email, password);

        MyAccountPage myAccountPage = new MyAccountPage();

        Assert.assertTrue(myAccountPage.isLoggedIn());

        myAccountPage.openWishlists();
        WishListsPage wishListsPage = new WishListsPage();

        Assert.assertTrue(wishListsPage.isWishlistEmpty());

        wishListsPage.createWishlist(wishlistName);

        Assert.assertTrue(wishListsPage.isWishlistExist(wishlistName));

        wishListsPage.goHomePage();

        HomeProductPage homeProductPage = new HomeProductPage();
        homeProductPage.viewProduct(productName);

        ProductViewPage productViewPage = new ProductViewPage();
        productViewPage.addProductToWishlist();
        productViewPage.goAccountPage();

        myAccountPage.openWishlists();

        Assert.assertEquals(1, wishListsPage.getWishlistsCount());
        Assert.assertTrue(wishListsPage.getWishlistProducts().contains(productName));
    }
}
