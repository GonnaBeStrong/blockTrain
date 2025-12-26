package com.sixoneseven.blocktrain.util;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class HashUtil {

    public static String sha256(java.io.File file) {
        try (FileInputStream fis = new FileInputStream(file)) {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[8192];
            int len;

            while ((len = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }

            return bytesToHex(digest.digest());
        } catch (Exception e) {
            throw new RuntimeException("文件 Hash 计算失败", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
