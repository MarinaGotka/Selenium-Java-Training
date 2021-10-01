package selenium;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Utilities {

    public static void takeScreenshot(WebDriver driver)
    {
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File("%s\\screenshot.png", Paths.get("").toAbsolutePath().toString()));
        } catch (IOException e) {
            System.out.println("Screenshot can't be saved.");
        }
    }
}
