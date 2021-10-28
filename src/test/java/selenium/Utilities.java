package selenium;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Utilities {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(String fileName)
    {
        TakesScreenshot scrShot =((TakesScreenshot)WebDriverFactory.driver);
        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);

        try {
            if(fileName == null)
            {
                fileName = "screenshot";
            }

            File file = new File(Paths.get("").toAbsolutePath().toString() + fileName + ".png");
            FileUtils.copyFile(srcFile, file);

            System.out.println(String.format("Screenshot is saved as %s.",file));

            return  scrShot.getScreenshotAs(OutputType.BYTES);
        } catch (IOException e) {
            System.out.println("Screenshot can't be saved.");
            return  null;
        }
    }
}
