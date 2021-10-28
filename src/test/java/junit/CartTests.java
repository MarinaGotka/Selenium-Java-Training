package junit;

import org.junit.jupiter.api.DisplayName;
import shop.Cart;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import java.io.*;

public class CartTests {

    @DisplayName("Check name and total price of created Cart")
    @Test
    void cartNameAndPriceTest() {
        String expectedName = "test-cart";
        Cart testCart = new Cart(expectedName);

        assertAll("Cart name and price after cart creation",
                () -> assertEquals(expectedName, testCart.getCartName()),
                () -> assertEquals(0, testCart.getTotalPrice())
        );
    }

    @DisplayName("Calculate total after adding real item")
    @Test
    void calculateTotalTest() {
        String expectedName = "test-cart";
        double itemPrice = 5;
        double TAX = 0.2;

        Cart testCart = new Cart(expectedName);
        RealItem realItem = new RealItem();
        realItem.setPrice(itemPrice);
        testCart.addRealItem(realItem);

        double expectedTotal = itemPrice + itemPrice*TAX;
        double actualTotal = testCart.getTotalPrice();

        assertEquals( expectedTotal, actualTotal, 0);
    }

    @DisplayName("Create cart with real and virtual items and show it")
    @Test
    void showItemsTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Cart testCart = new Cart("test-cart");

        String name = "test-item";
        double itemPrice = 5.5;
        double itemWeight = 2076.5;

        RealItem realItem = new RealItem();
        realItem.setName(name);
        realItem.setPrice(itemPrice);
        realItem.setWeight(itemWeight);

        String virtualName = "test-item";
        double virtualPrice = 5.5;
        double size = 500;

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(virtualName);
        virtualItem.setPrice(virtualPrice);
        virtualItem.setSizeOnDisk(size);

        testCart.addRealItem(realItem);
        testCart.addVirtualItem(virtualItem);
        testCart.showItems();

        String expectedString = String.format("Class: class shop.RealItem; Name: %s; Price: %s; Weight: %s\r\n", name, itemPrice, itemWeight);
        expectedString += String.format("Class: class shop.VirtualItem; Name: %s; Price: %s; Size on disk: %s", virtualName, virtualPrice, size);

        assertEquals(expectedString, outContent.toString().trim());
    }
}
