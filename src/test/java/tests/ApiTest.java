package tests;

import entities.Promotion;
import org.apache.hc.core5.http.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static api.Verifier.getOldestPromotion;
import static util.Logging.LOGGER;

public class ApiTest {

    @Test
    public void getOldest() throws URISyntaxException, IOException, ParseException {
        Promotion promotion = getOldestPromotion();
        LOGGER.info("Oldest promotion: " + promotion);
    }
}
