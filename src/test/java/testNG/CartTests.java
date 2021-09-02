package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CartTests {

    @Test(testName = "Check name and total price of created Cart", groups = { "group2" })
    void cartNameAndPriceTest() {
        String expectedName = "test-cart";
        Cart testCart = new Cart(expectedName);

        SoftAssert asert =new SoftAssert();

        asert.assertEquals(testCart.getCartName(), expectedName);
        asert.assertEquals(testCart.getTotalPrice(), 0.0);
        asert.assertAll();
    }

    @Test(testName = "Calculate total after adding real item")
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

        Assert.assertEquals( actualTotal, expectedTotal,0);
    }

    @Test(testName = "Create cart with real and virtual items and show it")
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

        Assert.assertEquals(outContent.toString().trim(), expectedString);
    }
}
