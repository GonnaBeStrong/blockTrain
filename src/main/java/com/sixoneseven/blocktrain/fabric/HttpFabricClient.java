package com.sixoneseven.blocktrain.fabric;

// ===== Spring =====
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.net.URL;
// ===== Java 基础 =====


@Component
@Profile("prod")
public class HttpFabricClient implements FabricClient {

    private static final String BASE_URL = "http://172.16.1.200:7000/api";

    @Override
    public String queryMetadata(String id)
    {
        try
        {
            HttpURLConnection conn = (HttpURLConnection)
                    new URL(BASE_URL+ "/data/" + id).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())))
            {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);
                String json = sb.toString();

                JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
                return obj.has("hashValue") ? obj.get("hashValue").getAsString() : null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }


    @Override
    public String putMetadata(String id, String hashValue)
    {
        try
        {
            String json = """
                {
                  "id": "%s",
                  "dataName": "1",
                  "dataType": "1",
                  "dataDescription": "1",
                  "owner": "1",
                  "createTime": "2025-12-27T20:00:00Z",
                  "dataSize": 1,
                  "hashValue": "%s",
                  "accessControl": "PUBLIC",
                  "version": "1"
                }
                """.formatted(id, hashValue);

            HttpURLConnection conn = (HttpURLConnection)
                    new URL(BASE_URL+"/data/create ").openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream())
            {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();
            String resp;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(code == 200 ? conn.getInputStream() : conn.getErrorStream())))
            {
                resp = br.lines().collect(Collectors.joining());
            }
        } catch (Exception e)
        {
        }
        return id;
    }
}
