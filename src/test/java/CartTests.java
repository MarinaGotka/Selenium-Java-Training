import org.junit.jupiter.api.DisplayName;
import shop.Cart;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CartTests {

    @DisplayName("Cart creation verification")
    @Test
    void GetCartNameTest() {
        String expectedName = "test-cart";
        Cart testCart = new Cart(expectedName);

        assertAll("cart",
                () -> assertEquals(testCart.getCartName(), expectedName),
                () -> assertEquals(testCart.getTotalPrice(),0)
        );
    }

    @DisplayName("Calculate total after adding real item")
    @Test
    void CalculateTotalTest() {
        String expectedName = "test-cart";
        double itemPrice = 5;
        double TAX = 0.2;

        Cart testCart = new Cart(expectedName);
        RealItem realItem = new RealItem();
        realItem.setPrice(itemPrice);
        testCart.addRealItem(realItem);

        double expectedTotal = itemPrice + itemPrice*TAX;
        double actualTotal = testCart.getTotalPrice();

        assertEquals(actualTotal, expectedTotal, 0);
    }
}
