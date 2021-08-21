import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonParserTests {

    private final JsonParser jsonParser = new JsonParser();

    private final String testFileName = "test-cart";
    private final String filePath = "./src/main/resources/%s.json";

    @DisplayName("Write to file - valid cart with full information")
    @Tag("WriteGroup")
    @Test
    public void WriteValidCartToFileTest() {
        Cart testCart = createValidCart();

        String expected = "{\"cartName\":\"test-cart\"," +
                "\"realItems\":[{\"weight\":1560.0,\"name\":\"Audi\",\"price\":32026.9}]," +
                "\"virtualItems\":[{\"sizeOnDisk\":20000.0,\"name\":\"Windows\",\"price\":11.0}],\"total\":38445.479999999996}";

        jsonParser.writeToFile(testCart);

        try {
            String path = String.format(filePath, testFileName);
            assertEquals(readFileAsString(path), expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Write to file - empty cart with only name")
    @Disabled("Disabled test")
    @Tag("WriteGroup")
    @Test
    public void WriteEmptyCartToFileTest() {
        Cart testCart = new Cart(testFileName);

        String expected = "{\"cartName\":\"test-cart\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";

        jsonParser.writeToFile(testCart);

        try {
            String path = String.format(filePath, testFileName);
            assertEquals(readFileAsString(path), expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Read from file - valid file")
    @Tag("ReadGroup")
    @Test
    public void ReadValidCartFromFileTest() {
        Cart testCart = createValidCart();
        File file = new File(String.format(filePath, testFileName));

        jsonParser.writeToFile(testCart);

        assertEquals(jsonParser.readFromFile(file).getCartName(), testCart.getCartName());
        assertEquals(jsonParser.readFromFile(file).getTotalPrice(), testCart.getTotalPrice());
    }

    @DisplayName("Read from file with incorrect name - No Such FIle exception is expected")
    @Tag("ReadGroup")
    @ParameterizedTest
    @ValueSource(strings = {"d", "*", "123", ".#$%", " "})
    public void ReadCartFromInvalidFileTest(String fileName) {
        File file = new File(String.format(filePath, fileName));

        assertThrows(NoSuchFileException.class,() -> jsonParser.readFromFile(file));
    }

    @AfterEach
    public void cleanUpEach() throws IOException {

        Path path = FileSystems.getDefault().getPath(String.format(filePath, testFileName));

        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    private Cart createValidCart(){
        Cart testCart = new Cart(testFileName);

        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        testCart.addRealItem(car);
        testCart.addVirtualItem(disk);

        return testCart;
    }

    private String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
