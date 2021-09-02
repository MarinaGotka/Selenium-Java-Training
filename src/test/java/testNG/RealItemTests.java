package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;
import shop.RealItem;

public class RealItemTests {

    @Test(testName = "Verify String representation", groups = { "group1" })
    void stringRepresentationTest() {
        String expectedName = "name";
        double expectedWeight = 2;

        RealItem realItem = new RealItem();
        realItem.setName(expectedName);
        realItem.setWeight(expectedWeight);

        String expectedString = String.format("Class: class shop.RealItem; Name: %s; Price: %s; Weight: %s", expectedName, 0.0, expectedWeight);

        Assert.assertEquals(realItem.toString(), expectedString);
    }
}
