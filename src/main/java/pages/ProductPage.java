package pages;

import entities.Product;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static entities.Product.createProduct;
import static util.Waits.*;

public class ProductPage {

    public ProductPage (WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".magnify-image img")
    private WebElement productImage;

    @FindBy(css = ".product-title h1")
    private WebElement productTitle;

    @FindBy(css = ".ppra_price-number")
    private WebElement newPrice;

    @FindBy(css = ".ppra_old-price")
    private WebElement oldPrice;

    @FindBy(css = ".ppra_mp-price")
    private WebElement fallbackOldPrice;

    @FindBy(css = ".ppra_saving-price-number")
    private WebElement priceSaving;

    @FindBy(css = ".ppra_price-and-saving-price-wrapper")
    private WebElement priceWrapper;

    public Product checkProductPageOpened() {
        waitForElement(productTitle);
        waitForElement(priceWrapper);
        String oldPriceText = null;
        String priceSavingText = null;
        try{
            oldPriceText = oldPrice.getText();
            priceSavingText = priceSaving.getText();
        } catch (NoSuchElementException e) {
            /*
            selector for old price changed during work on task
            this ensures it is read
             */
            try {
                oldPriceText = fallbackOldPrice.getText();
                priceSavingText = priceSaving.getText();
            } catch (NoSuchElementException ex) {
            }
        }
        return createProduct(productTitle.getText(), oldPriceText, newPrice.getText(), priceSavingText);
    }
}
