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

    // 🔗 模拟 Fabric 世界状态（链上账本）
    private static final Map<String, String> WORLD_STATE = new ConcurrentHashMap<>();

    @Override
    public String putMetadata(String assetId, String hash) {
        try {
            // 1️⃣ 存“链上状态”
            WORLD_STATE.put(assetId, hash);

            // 2️⃣ 模拟 Fabric TxID
            String raw = assetId + hash + Instant.now().toEpochMilli() + UUID.randomUUID();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(bytes);
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
