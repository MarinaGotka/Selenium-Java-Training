package selenium.tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.Utilities;
import selenium.WebDriverFactory;
import selenium.pages.HomeMailPage;
import selenium.pages.LoginPage;

public class LoginTests extends TestBase{

    @DisplayName("Login with correct credentials")
    @Description("Verify login with correct credentials.")
    @ParameterizedTest
    @CsvSource({"java1.test,1QAZ2wsx ", "java2.test,ZAQ1xsw2"})
    void loginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(WebDriverFactory.driver);
        loginPage.goToURL();
        loginPage.login(username, password);

        HomeMailPage homeMailPage = new HomeMailPage(WebDriverFactory.driver);

        Assert.assertTrue(homeMailPage.isLoggedIn());
    }

    @DisplayName("Login with correct credentials and logout")
    @Description("Verify login with correct credentials and logout.")
    @Test
    void logoutTest() {
        String username = "java1.test";
        String password = "1QAZ2wsx";

        LoginPage loginPage = new LoginPage(WebDriverFactory.driver);
        loginPage.goToURL();
        loginPage.login(username, password);

        HomeMailPage homeMailPage = new HomeMailPage(WebDriverFactory.driver);

        Assert.assertTrue(homeMailPage.isLoggedIn());

        homeMailPage.logout();

        Utilities.takeScreenshot("123");
        Assert.assertFalse(!homeMailPage.isLoggedIn());
    }
}
