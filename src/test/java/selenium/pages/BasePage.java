package selenium.pages;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class BasePage {

    protected static WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void takeScreenshot()
    {
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(String.format("C:\\Users\\screenshot.png")));
        } catch (IOException e) {
            System.out.println("Screenshot can't be saved.");
        }
    }
}
