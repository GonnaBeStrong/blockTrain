package com.sixoneseven.blocktrain.fabric;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.UUID;

@Component
public class MockFabricClient implements FabricClient {

    @Override
    public String putMetadata(String assetId, String hash) {
        try {
            // 模拟 Fabric 的 TxID 生成规则
            String raw = assetId + hash + Instant.now().toEpochMilli() + UUID.randomUUID();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Mock Fabric 上链失败", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
