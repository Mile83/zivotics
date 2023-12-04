package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static util.Waits.waitForElementsToBeDisplayed;

public class WebElementActions {

    public static void clickElementFromList(int position, List<WebElement> webElements, WebDriver driver) {
        waitForElementsToBeDisplayed(webElements);
        new Actions(driver).scrollToElement(webElements.get(position)).perform();
        webElements.get(position).click();
    }
}
