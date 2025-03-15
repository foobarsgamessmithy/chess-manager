package eu.foobarssgamesmithy.chessmanager.common;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;

@Component
public class HttpClient {

    private final RestTemplate restTemplate;

    public HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String get(String url, HashMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        params.forEach((key, value) -> uriComponentsBuilder.queryParam(key, "{" + key + "}"));
        String urlTemplate = uriComponentsBuilder.encode().toUriString();

        ResponseEntity<String> result = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity,
                String.class, params);
        return result.getBody();
    }

    public static <T> HttpEntity<T> getStandardHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }
}
