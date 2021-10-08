package selenium;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.IOException;

public class ScreenshotOnFailure implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        if (throwable instanceof IOException) {
            return;
        }
        Utilities.takeScreenshot(context.getDisplayName());
        throw throwable;
    }
}