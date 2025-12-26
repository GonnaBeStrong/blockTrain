package com.sixoneseven.blocktrain.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static java.io.File buildTrustedZip(
            com.sixoneseven.blocktrain.repo.entity.File asset,
            String chainHash
    ) throws Exception {

        // 生成临时 zip 文件
        java.io.File zipFile = java.io.File.createTempFile("trusted_data_", ".zip");

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {

            // 1️⃣ 原始文件（java.io.File）
            java.io.File dataFile = new java.io.File(asset.getPath());
            addFile(zos, dataFile, "data/" + dataFile.getName());

            // 2️⃣ metadata.json（数据库信息）
            addJson(zos, "metadata.json", Map.of(
                    "dataName", asset.getName(),
                    "dataType", asset.getForm(),
                    "uploadTime", asset.getUploadTime().toString(),
                    "provider", asset.getFrom()
            ));

            // 3️⃣ credential.json（可信凭证）
            addJson(zos, "credential.json", Map.of(
                    "txId", asset.getLite(), // 如果你链上 txId 存在别的字段，请改这里
                    "fileHash", chainHash,
                    "verifyResult", "PASS",
                    "blockchain", "Hyperledger Fabric"
            ));
        }

        return zipFile;
    }


    private static void addFile(
            ZipOutputStream zos,
            java.io.File file,
            String entryName) throws IOException {

        zos.putNextEntry(new ZipEntry(entryName));
        Files.copy(file.toPath(), zos);
        zos.closeEntry();
    }

    private static void addJson(
            ZipOutputStream zos,
            String name,
            Map<String, Object> data) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        byte[] json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsBytes(data);

        zos.putNextEntry(new ZipEntry(name));
        zos.write(json);
        zos.closeEntry();
    }
}

