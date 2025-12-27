package com.sixoneseven.blocktrain.fabric;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Component
@Profile("dev")
public class MockFabricClient implements FabricClient {

    // 🔗 world state: assetId -> hash
    private static final Map<String, String> WORLD_STATE =
            new ConcurrentHashMap<>();

    @Override
    public String putMetadata(String assetId, String hash) {
        try {
            // 模拟 txId（仅用于返回）
            String raw = assetId + hash + System.currentTimeMillis() + UUID.randomUUID();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String txId = bytesToHex(
                    digest.digest(raw.getBytes(StandardCharsets.UTF_8))
            );

            // ✅ 正确：assetId -> hash
            WORLD_STATE.put(assetId, hash);

            return txId;
        } catch (Exception e) {
            throw new RuntimeException("Mock Fabric 上链失败", e);
        }
    }

    @Override
    public String queryMetadata(String assetId) {
        return WORLD_STATE.get(assetId);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
