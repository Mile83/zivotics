package api;

import entities.Promotion;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Verifier {

    public static boolean verifyPageUp() throws IOException {
        return Request.pingGigatron() == 200;
    }

    public static Promotion getOldestPromotion() throws URISyntaxException, IOException, ParseException {
        List<Promotion> promotionList = Request.getPromotions();
        List<Promotion> sortedPromotionList = promotionList.stream().sorted(Comparator.comparing(Promotion::getStartTime)).collect(Collectors.toList());
        return sortedPromotionList.get(0);
    }
}
