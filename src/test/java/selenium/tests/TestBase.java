package selenium.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import selenium.ScreenshotOnFailure;
import selenium.WebDriverFactory;

@ExtendWith(ScreenshotOnFailure.class)
public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        WebDriverFactory.initialize("firefox");
    }

    @AfterAll
    public static void afterAll() {
        WebDriverFactory.quit();
    }
}
