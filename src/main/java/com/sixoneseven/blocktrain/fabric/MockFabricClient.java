package com.sixoneseven.blocktrain.fabric;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MockFabricClient implements FabricClient {

    // 🔗 txId -> fileHash
    private static final Map<String, String> WORLD_STATE =
            new ConcurrentHashMap<>();

    @Override
    public String putMetadata(String assetId, String hash) {
        try {
            // 1️⃣ 生成 txId
            String raw = assetId + hash + Instant.now().toEpochMilli() + UUID.randomUUID();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String txId = bytesToHex(
                    digest.digest(raw.getBytes(StandardCharsets.UTF_8))
            );

            // 2️⃣ 链上状态：txId -> hash
            WORLD_STATE.put(txId, hash);

            return txId;
        } catch (Exception e) {
            throw new RuntimeException("Mock Fabric 上链失败", e);
        }
    }

    @Override
    public String queryMetadata(String txId) {
        return WORLD_STATE.get(txId);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

