package util;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

public class Waits {

    public static void waitForElement(WebElement webElement) {
        try {
            await()
                    .atMost(10, TimeUnit.SECONDS)
                    .pollInterval(100, TimeUnit.MILLISECONDS)
                    .ignoreExceptions()
                    .until(webElement::isEnabled);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForElementsToBeDisplayed(List<WebElement> webElements) {
        try {
            await()
                    .atMost(10, TimeUnit.SECONDS)
                    .pollInterval(100, TimeUnit.MILLISECONDS)
                    .ignoreExceptions()
                    .until(() -> webElements.size() > 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
