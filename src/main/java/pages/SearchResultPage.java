package pages;

import entities.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static util.Waits.*;
import static util.WebElementActions.clickElementFromList;

public class SearchResultPage {
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;

    @FindBy(css = "#grid-products .item")
    private List<WebElement> productItemList;

    @FindBy(css = "a.item__name h4")
    private List<WebElement> productNames;

    @FindBy(css = ".item__bottom__prices__price")
    private List<WebElement> newPrices;

    @FindBy(css = ".item__bottom__prices__old")
    private List<WebElement> oldPrices;

    @FindBy(css = ".item__bottom__prices__saving")
    private List<WebElement> priceSavings;

    @FindBy(css = "a.item__name")
    private List<WebElement> productNameLinks;

    public void checkSearchReturnItems() {
        waitForElementsToBeDisplayed(productItemList);
    }

    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        waitUntilPageElementsDisplayed();
        for(int i = 0; i<productItemList.size(); i++) {
            Product product = getProduct(i);
            productList.add(product);
        }
        return productList;
    }

    public void clickProduct(int position) {
        clickElementFromList(position, productNameLinks, driver);
    }

    private Product getProduct(int index) {
        return Product.createProduct(productNames.get(index).getText(), oldPrices.get(index).getText(), newPrices.get(index).getText(), priceSavings.get(index).getText());
    }

    private void waitUntilPageElementsDisplayed(){
        waitForElementsToBeDisplayed(productItemList);
        waitForElementsToBeDisplayed(productNames);
        waitForElementsToBeDisplayed(oldPrices);
        waitForElementsToBeDisplayed(newPrices);
        waitForElementsToBeDisplayed(priceSavings);
    }
}
