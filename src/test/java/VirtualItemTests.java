import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualItemTests {

    @DisplayName("Verify SizeOnDisk property")
    @Test
    void SizeOnDiskTest() {
        double expectedSizeOn= 20000;

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(expectedSizeOn);

        double actualSizeOn = virtualItem.getSizeOnDisk();

        assertEquals(actualSizeOn, expectedSizeOn, 0);
    }
}
