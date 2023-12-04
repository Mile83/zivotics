package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.Header;
import pages.ProductPage;
import pages.SearchResultPage;

import java.io.IOException;

import static api.Request.initRequestHelper;
import static util.Constants.COOKIE_KEY;
import static util.Constants.COOKIE_VALUE;

public class TestBase {

    private static WebDriver driver;
    private static CloseableHttpClient client;

    public static Header header;
    public static SearchResultPage searchResultPage;
    public static ProductPage productPage;

    public static WebDriver getWebDriver() {
        return driver;
    }

    @BeforeSuite
    public void apiSetup() {
        client = HttpClients.createDefault();
        initRequestHelper(client);
    }

    @AfterSuite
    public void closeApiClient() throws IOException {

        if (client != null) {
            client.close();
        }
    }

    @AfterMethod
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

    public void frontendSetup(String url) {
        ChromeOptions options = setChromeOptions();
        setupChromeDriver(options, url);
        initAllPages();
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    private void setupChromeDriver(ChromeOptions options, String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().addCookie(new Cookie(COOKIE_KEY, COOKIE_VALUE));
        driver.navigate().refresh();
    }

    private void initAllPages() {
        header = new Header(getWebDriver());
        searchResultPage = new SearchResultPage(getWebDriver());
        productPage = new ProductPage(getWebDriver());
    }
}
