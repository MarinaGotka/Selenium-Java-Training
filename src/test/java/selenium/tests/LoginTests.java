package selenium.tests;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.pageFactory.HomeMailPage;
import selenium.pageFactory.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginTests {

    private final String URL = "https://mail.yandex.com/";

    private WebDriver driver;

    @BeforeEach
    public void setup(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @DisplayName("Login with correct credentials")
    @ParameterizedTest
    @CsvSource({"java1.test,1QAZ2wsx ", "java2.test,ZAQ1xsw2"})
    void loginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);
        HomeMailPage homeMailPage = loginPage.login(username, password);

        Assert.assertTrue(homeMailPage.isLoggedIn());
    }

    @DisplayName("Login with correct credentials and logout.")
    @Test
    void logoutTest() {
        String username = "java1.test";
        String password = "1QAZ2wsx";

        LoginPage loginPage = new LoginPage(driver);
        HomeMailPage homeMailPage = loginPage.login(username, password);

        Assert.assertTrue(homeMailPage.isLoggedIn());

        homeMailPage.logout();

        Assert.assertFalse(homeMailPage.isLoggedIn());
    }

    @AfterEach
    public void tearDown(){

        driver.close();
        driver.quit();
    }
}
