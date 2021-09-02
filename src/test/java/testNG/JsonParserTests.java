package testNG;

import org.testng.Assert;
import org.testng.annotations.*;
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

public class JsonParserTests {

    private final JsonParser jsonParser = new JsonParser();

    private final String testFileName = "test-cart";
    private final String filePath = "./src/main/resources/%s.json";

    @Test(testName = "Write to file - valid cart with full information", groups = { "group1" })
    public void writeValidCartToFileTest() {
        Cart testCart = createValidCart();

        String expected = "{\"cartName\":\"test-cart\"," +
                "\"realItems\":[{\"weight\":1560.0,\"name\":\"Audi\",\"price\":32026.9}]," +
                "\"virtualItems\":[{\"sizeOnDisk\":20000.0,\"name\":\"Windows\",\"price\":11.0}],\"total\":38445.479999999996}";

        jsonParser.writeToFile(testCart);

        try {
            String path = String.format(filePath, testFileName);
            Assert.assertEquals(readFileAsString(path), expected);
        } catch (Exception e) {
            Assert.fail("Exception: Can not read file as string");
        }
    }

    @Test(testName = "Write to file - empty cart with only name", groups = { "group1" }, enabled = false)
    public void writeEmptyCartToFileTest() {
        Cart testCart = new Cart(testFileName);

        String expected = "{\"cartName\":\"test-cart\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";

        jsonParser.writeToFile(testCart);

        try {
            String path = String.format(filePath, testFileName);
            Assert.assertEquals(readFileAsString(path), expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(testName = "Read from file - valid file", groups = { "group2" })
    public void readValidCartFromFileTest() {
        Cart testCart = createValidCart();
        File file = new File(String.format(filePath, testFileName));

        jsonParser.writeToFile(testCart);

        Assert.assertEquals(jsonParser.readFromFile(file).getCartName(), testCart.getCartName());
        Assert.assertEquals(jsonParser.readFromFile(file).getTotalPrice(), testCart.getTotalPrice());
    }

    @Test(testName = "Read from file with incorrect name - No Such FIle exception is expected", groups = { "group2" }, dataProvider = "test1")
    public void readCartFromInvalidFileTest(String fileName) {
        File file = new File(String.format(filePath, fileName));

        Assert.assertThrows(NoSuchFileException.class,() -> jsonParser.readFromFile(file));
    }

    @Parameters({"fileName"})
    @Test(testName = "XML - Read from file with incorrect name - No Such FIle exception is expected", groups = { "group1" })
    public void readCartFromInvalidFileTestXML(@Optional("optionalFileName") String fileName) {
        File file = new File(String.format(filePath, fileName));

        Assert.assertThrows(NoSuchFileException.class,() -> jsonParser.readFromFile(file));
    }

    @AfterTest
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

    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][]{{"d"}, {"*"}, {"123"}, {".#$%"}, {" "}};
    }
}
