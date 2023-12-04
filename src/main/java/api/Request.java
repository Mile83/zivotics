package api;

import entities.Promotion;
import entities.SlideResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static util.Constants.GIGATRON;
import static util.Constants.GET_SLIDESHOW;

public class Request {

    private static RequestHelper requestHelper;

    public static void initRequestHelper(CloseableHttpClient client) {
        requestHelper = new RequestHelper(client);
    }

    public static int pingGigatron() throws IOException {
        return requestHelper.getRequest(GIGATRON);
    }

    public static List<Promotion> getPromotions() throws URISyntaxException, IOException {
        String response = requestHelper.postRequest(GET_SLIDESHOW, null);

        return ResponseParser.parse(response, SlideResponse.class).slides;
    }
}
