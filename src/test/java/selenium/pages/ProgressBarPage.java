package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage {
    private final String PROGRESS_BAR_URL = "https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html";

    private final By downloadButton = By.cssSelector("#cricle-btn");
    private final By percentElement = By.cssSelector(".percenttext");

    private static WebDriver driver;

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PROGRESS_BAR_URL);
    }

    public void startDownload() {
        driver.findElement(downloadButton).click();
    }

    public void waitUntilPercent(int percent) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(Duration.ofMillis(10));
        wait.until(ExpectedConditions.textToBe(percentElement, percent + "%"));
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public int getPercentValue() {
        String percentText = driver.findElement(percentElement).getText();

        //remove '%' char and return int value
        return Integer.valueOf(percentText.substring(0, percentText.length() - 1));
    }
}
