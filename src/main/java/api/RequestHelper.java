package api;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class RequestHelper {

    CloseableHttpClient client;

    public RequestHelper(CloseableHttpClient client) {
        this.client = client;
    }

    Integer getRequest(String endpoint) throws IOException {
        HttpGet get = new HttpGet(endpoint);
        return client.execute(get, new StatusCodeResponseHandler());
    }

    String  postRequest(String endpoint, HashMap<String, String> params) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(endpoint);
        if (params != null) {
            for (String paramKey : params.keySet()) {
                builder.setParameter(paramKey, params.get(paramKey));
            }
        }
        HttpPost post = new HttpPost(builder.build());
        return client.execute(post, new BasicHttpClientResponseHandler());
    }
}
