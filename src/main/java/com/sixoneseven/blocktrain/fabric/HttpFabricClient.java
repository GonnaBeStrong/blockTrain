package com.sixoneseven.blocktrain.fabric;

// ===== Spring =====
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// ===== Java 基础 =====
import java.util.Map;

@Component
@Profile("prod")
public class HttpFabricClient implements FabricClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "http://172.16.1.200:7000/api";

    @Override
    public String queryMetadata(String assetId) {

        String url = BASE_URL + "/data/" + assetId;
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String putMetadata(String assetId, String fileHash) {

        Map<String, Object> body = Map.of(
                "id", assetId,
                "hashValue", fileHash
        );

        return restTemplate.postForObject(
                BASE_URL + "/data",
                body,
                String.class
        );
    }
}
