package selenium.tests;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.pages.HomeMailPage;
import selenium.pages.LoginPage;

public class LoginTests extends TestBase{

    @DisplayName("Login with correct credentials")
    @ParameterizedTest
    @CsvSource({"java1.test,1QAZ2wsx ", "java2.test,ZAQ1xsw2"})
    void loginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.GoToURL();
        loginPage.login(username, password);

        HomeMailPage homeMailPage = new HomeMailPage(driver);

        Assert.assertTrue(homeMailPage.isLoggedIn());
    }

    @DisplayName("Login with correct credentials and logout.")
    @Test
    void logoutTest() {
        String username = "java1.test";
        String password = "1QAZ2wsx";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.GoToURL();
        loginPage.login(username, password);

        HomeMailPage homeMailPage = new HomeMailPage(driver);

        Assert.assertTrue(homeMailPage.isLoggedIn());

        homeMailPage.logout();

        Assert.assertFalse(homeMailPage.isLoggedIn());
    }
}
