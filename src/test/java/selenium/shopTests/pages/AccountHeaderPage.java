package selenium.shopTests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountHeaderPage extends BasePage{

    @FindBy(className = "account")
    WebElement accountName;

    @FindBy(css = ".header_user_info .logout")
    WebElement signOut;

    @FindBy(className = "icon-home")
    WebElement homeBtn;

    @FindBy(css = ".shopping_cart a")
    WebElement cartButton;

    public void signOut() {
        signOut.click();
    }

    public void goAccountPage() {
        accountName.click();
    }

    public void goCartPage() {
        cartButton.click();
    }

    public void goHomePage() {
        homeBtn.click();
    }
}
