import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.RealItem;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTests {

    @DisplayName("Verify weight property")
    @Test
    void WeightTest() {
        double expectedWeight = 2;

        RealItem realItem = new RealItem();
        realItem.setWeight(expectedWeight);

        double actualWeight = realItem.getWeight();

        assertEquals(actualWeight, expectedWeight, 0);
    }
}
