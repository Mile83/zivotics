package tests;

import entities.Product;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static api.Verifier.verifyPageUp;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static util.Constants.GIGATRON;
import static util.Logging.LOGGER;
import static util.Products.*;

public class FrontEndTest extends TestBase {
    @BeforeClass
    public void checkIfUpAndRunning() throws IOException {
        assertTrue(verifyPageUp());
        LOGGER.info("Page is up and running");
    }

    @BeforeMethod
    public void setup() {
        frontendSetup(GIGATRON);
    }

    @Test
    public void searchAndCheckProduct() {
        searchProduct("monitor");
        List<Product> productList = searchResultPage.getProductList();
        calculateAveragePrice(productList);
        getSpecificProductInfo(productList);
        verifyProduct(productList);
    }

    private void searchProduct(String product) {
        header.inputSearch(product);
        searchResultPage.checkSearchReturnItems();
        LOGGER.info("Searched for: " + product);
    }

    private void calculateAveragePrice(List<Product> productList) {
        float averagePrice = getAveragePrice(productList);
        LOGGER.info("Calculated average price: " + averagePrice);
    }

    private void getSpecificProductInfo(List<Product> productList) {
        Product product;
        int productListSize = productList.size();
        if (productListSize % 2 == 0) {
            product = getNthSmallest(productList, 2);
            LOGGER.info("Even number of products present. Showing info for product with 2nd smallest price.");
        } else {
            product = closestToAverage(productList);
            LOGGER.info("Odd number of products present. Showing info for product with price closest to average.");
        }
        LOGGER.info("Product info: " + product);
    }

    private void verifyProduct(List<Product> productList) {
        int productPosition = getRandomProductPosition(productList);
        Product expectedProduct = productList.get(productPosition);
        LOGGER.info("Opening product on position: " + productPosition);
        searchResultPage.clickProduct(productPosition);
        Product openedProduct = productPage.checkProductPageOpened();
        assertEquals(openedProduct, expectedProduct);
    }

}
