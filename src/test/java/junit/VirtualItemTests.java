package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualItemTests {

    @DisplayName("Verify SizeOnDisk property")
    @Test
    void sizeOnDiskTest() {
        double expectedSizeOn= 20000;

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(expectedSizeOn);

        double actualSizeOn = virtualItem.getSizeOnDisk();

        assertEquals(actualSizeOn, expectedSizeOn, 0);
    }

    @DisplayName("Verify String representation")
    @Test
    void stringRepresentationTest() {
        String expectedName = "name";
        double expectedSize = 200;

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(expectedName);
        virtualItem.setSizeOnDisk(expectedSize);

        String expectedString = String.format("Class: class shop.VirtualItem; Name: %s; Price: %s; Size on disk: %s", expectedName, 0.0, expectedSize);

        assertEquals(expectedString, virtualItem.toString());
    }
}
