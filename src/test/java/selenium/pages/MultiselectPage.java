package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class MultiselectPage extends BasePage{
    private final String URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

    @FindBy(id = "multi-select")
    WebElement select;

    @FindBy(xpath = "//select[@id = 'multi-select']/option")
    List<WebElement> options;

    Select selectElement;

    public MultiselectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String GetURL() {
        return URL;
    }

    public void selectRandomOptions(int numberOptions) {
        selectElement = new Select(select);

        for (int i = numberOptions; i > 0; i--) {
            int index = new Random().nextInt(options.size());
            selectElement.selectByIndex(index);
        }
    }

    public List<WebElement> getAllSelectedOptions() {
        return selectElement.getAllSelectedOptions();
    }
}
