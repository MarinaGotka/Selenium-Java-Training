package selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import selenium.ScreenshotOnFailure;
import selenium.WebDriverFactory;

@ExtendWith(ScreenshotOnFailure.class)
public class TestBase {

    @BeforeEach
    public void setup() {
        WebDriverFactory.initialize("chrome");
    }

    @AfterEach
    public void cleanUp() {
        WebDriverFactory.quit();
    }
}
