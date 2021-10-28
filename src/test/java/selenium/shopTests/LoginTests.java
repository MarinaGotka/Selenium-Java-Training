package selenium.shopTests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.Utilities;
import selenium.shopTests.pages.AuthenticationPage;
import selenium.shopTests.pages.MyAccountPage;
import selenium.shopTests.pages.RegistrationPage;
import selenium.shopTests.pages.StartPage;

public class LoginTests extends TestBase {

    final String email = "java1.test@yandex.com";
    final String password = "1qaz2wsx";

    final String newEmail = String.format("%s@%s", Utilities.getUniqueId(), "gmail.com");
    final String newPassword = "Password123";

    @DisplayName("Create new account")
    @Description("Verify account creation.")
    @Test
    void createAccountTest() {
        String firstName = "Name";
        String lastName = "LastName";
        String address = "Address, 23, 3st.";
        String city = "CityName";
        String state = "Florida";
        String postalCode = "11111";
        String phone = "1234567";

        StartPage startPage = new StartPage();
        startPage.invokeSignIn();

        AuthenticationPage authenticationPage = new AuthenticationPage();
        authenticationPage.createAccount(newEmail);

        RegistrationPage registrationPage = new RegistrationPage();

        Assert.assertTrue(registrationPage.isOpened());

        registrationPage.createAccount(firstName, lastName, newPassword, address, city, state, postalCode, phone);

        MyAccountPage myAccountPage = new MyAccountPage();

        Assert.assertTrue(myAccountPage.isLoggedIn());
    }

    @DisplayName("Login with correct credentials")
    @Description("Verify login with correct credentials")
    @Test
    void loginTestCorrect() {
        StartPage startPage = new StartPage();
        startPage.invokeSignIn();

        AuthenticationPage authenticationPage = new AuthenticationPage();
        authenticationPage.login(email, password);

        MyAccountPage myAccountPage = new MyAccountPage();

        Assert.assertTrue(myAccountPage.isLoggedIn());
    }

    @DisplayName("Login with incorrect credentials")
    @Description("Verify login with incorrect credentials")
    @ParameterizedTest
    @CsvSource({"wrong.email1@gmail.com,12345 ", "email,Password123"})
    void loginTestIncorrect(String email, String password) {
        StartPage startPage = new StartPage();
        startPage.invokeSignIn();

        AuthenticationPage authenticationPage = new AuthenticationPage();
        authenticationPage.login(email, password);

        MyAccountPage myAccountPage = new MyAccountPage();

        Assert.assertFalse(myAccountPage.isLoggedIn());
    }
}
