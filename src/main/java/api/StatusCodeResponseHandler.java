package api;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

public class StatusCodeResponseHandler implements HttpClientResponseHandler<Integer> {
    @Override
    public Integer handleResponse(ClassicHttpResponse classicHttpResponse) {
        return classicHttpResponse.getCode();
    }
}
