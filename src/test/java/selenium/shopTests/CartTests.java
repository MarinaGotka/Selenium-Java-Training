package selenium.shopTests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenium.shopTests.pages.*;

import java.util.Arrays;
import java.util.List;

public class CartTests extends TestBase {

    final String email = "java1.test@yandex.com";
    final String password = "1qaz2wsx";

    @AfterEach
    public void testCleanup() {
        CartPage cartPage = new CartPage();
        cartPage.deleteAllCartProducts();

        cartPage.signOut();
    }

    @DisplayName("Verify the ability to add to cart ")
    @Description("Login, add products to cart, verify products are added")
    @Test
    void addProductToCartTest() {
        List<String> productNamesToAdd = Arrays.asList("Blouse", "Printed Dress", "Printed Summer Dress");

        StartPage startPage = new StartPage();
        startPage.invokeSignIn();

        AuthenticationPage authenticationPage = new AuthenticationPage();
        authenticationPage.login(email, password);

        MyAccountPage myAccountPage = new MyAccountPage();

        Assert.assertTrue(myAccountPage.isLoggedIn());

        myAccountPage.goHomePage();
        HomeProductPage homeProductPage = new HomeProductPage();

        for (String product: productNamesToAdd) {
            homeProductPage.viewProduct(product);

            ProductViewPage productViewPage = new ProductViewPage();
            productViewPage.addProductToCart();
            productViewPage.goHomePage();
        }

        homeProductPage.goCartPage();
        CartPage cartPage = new CartPage();

        Assert.assertEquals(productNamesToAdd.size(), cartPage.getCartProductCount());
        Assert.assertEquals(productNamesToAdd, cartPage.getCartProducts());
    }
}
