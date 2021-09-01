package selenium;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests {

    private final String url = "https://mail.yandex.com/";
    private final String username = "java1.test";
    private final String password = "1QAZ2wsx";

    private WebDriver driver;

    @BeforeEach
    public void setup(){

        driver = new ChromeDriver();
        driver.get(url);
    }

    @DisplayName("Login with correct credentials")
    @Test
    void loginTest() {

       LoginPage loginPage = new LoginPage(driver);
       loginPage.login(username, password);

        Assert.assertTrue(loginPage.isLoggedIn());
    }

    @AfterEach
    public void tearDown(){

        driver.quit();
    }
}
