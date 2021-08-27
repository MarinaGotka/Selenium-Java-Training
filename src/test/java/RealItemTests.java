import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.RealItem;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTests {

    @DisplayName("Verify weight property")
    @Test
    void weightTest() {
        double expectedWeight = 2;

        RealItem realItem = new RealItem();
        realItem.setWeight(expectedWeight);

        double actualWeight = realItem.getWeight();

        assertEquals(actualWeight, expectedWeight, 0);
    }

    @DisplayName("Verify String representation")
    @Test
    void stringRepresentationTest() {
        String expectedName = "name";
        double expectedWeight = 2;

        RealItem realItem = new RealItem();
        realItem.setName(expectedName);
        realItem.setWeight(expectedWeight);

        String expectedString = String.format("Class: class shop.RealItem; Name: %s; Price: %s; Weight: %s", expectedName, 0.0, expectedWeight);

        assertEquals(expectedString, realItem.toString());
    }
}
