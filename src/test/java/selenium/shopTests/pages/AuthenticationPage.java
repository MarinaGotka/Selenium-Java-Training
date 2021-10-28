package selenium.shopTests.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage extends BasePage{

    @FindBy(id = "email_create")
    WebElement emailAddressFieldCreateAccount;

    @FindBy(id = "SubmitCreate")
    WebElement createAccountButton;

    @FindBy(id = "email")
    WebElement emailAddressFieldSignIn;

    @FindBy(id = "passwd")
    WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    WebElement signInButton;

    public void login(String email, String password) {

        emailAddressFieldSignIn.sendKeys(email);
        passwordField.sendKeys(password);

        signInButton.click();
    }

    public void createAccount(String email) {

        emailAddressFieldCreateAccount.sendKeys(email);
        createAccountButton.click();
    }

    protected boolean isOpened() {
        try {
            return signInButton.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
