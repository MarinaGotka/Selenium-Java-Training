package selenium.shopTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import selenium.ScreenshotOnFailure;
import selenium.WebDriverFactory;

@ExtendWith(ScreenshotOnFailure.class)
public class TestBase {

    private static final String URL = "http://automationpractice.com/";

    @BeforeAll
    public static void beforeAll() {
        WebDriverFactory.initialize("chrome");
    }

    @BeforeEach
    public void testSetUp() {
        WebDriverFactory.driver.get(URL);
    }

    @AfterAll
    public static void afterAll() {
        WebDriverFactory.quit();
    }
}
