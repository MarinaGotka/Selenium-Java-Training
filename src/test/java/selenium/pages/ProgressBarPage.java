package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage extends BasePage{
    private final String URL = "https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html";

    @FindBy(css = "#cricle-btn")
    WebElement downloadButton;

    @FindBy(css = ".percenttext")
    WebElement percentElement;

    public ProgressBarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getURL() {
        return URL;
    }

    public void startDownload() {
        downloadButton.click();
    }

    public void waitUntilPercent(int percent) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(Duration.ofMillis(10));
        wait.until(driver -> percentElement.getText().equals(percent + "%"));
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public int getPercentValue() {
        String percentText = percentElement.getText();

        //remove '%' char and return int value
        return Integer.valueOf(percentText.substring(0, percentText.length() - 1));
    }
}
