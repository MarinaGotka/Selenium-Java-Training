package testNG;

import org.testng.annotations.Test;
import shop.VirtualItem;

import static org.testng.Assert.*;

public class VirtualItemTests {

    @Test(testName = "Verify String representation")
    void stringRepresentationTest() {
        String expectedName = "name";
        double expectedSize = 200;

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(expectedName);
        virtualItem.setSizeOnDisk(expectedSize);

        String expectedString = String.format("Class: class shop.VirtualItem; Name: %s; Price: %s; Size on disk: %s", expectedName, 0.0, expectedSize);

        assertEquals(virtualItem.toString(), expectedString);
    }
}
