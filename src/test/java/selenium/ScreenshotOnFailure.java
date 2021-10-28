package selenium;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class ScreenshotOnFailure implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {

        Method method = extensionContext.getRequiredTestMethod();
        if (extensionContext.getExecutionException().isPresent()) {
            Utilities.takeScreenshot(method.getName());
        }
    }
}