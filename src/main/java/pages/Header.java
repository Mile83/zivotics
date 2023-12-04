package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static util.Waits.waitForElement;

public class Header {

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".header__center:not(.sticky-blue) input")
    private WebElement inputSearch;

    @FindBy(css = ".header__center:not(.sticky-blue) .search-icon i")
    private WebElement btnSearch;

    public void inputSearch(String item) {
        waitForElement(inputSearch);
        inputSearch.sendKeys(item);
        search();
    }

    public void search() {
        waitForElement(btnSearch);
        btnSearch.click();
    }
}
