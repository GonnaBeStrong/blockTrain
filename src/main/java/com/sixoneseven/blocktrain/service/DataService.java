package com.sixoneseven.blocktrain.service;

import com.sixoneseven.blocktrain.repo.entity.DataAsset;
import com.sixoneseven.blocktrain.fabric.FabricClient;
import com.sixoneseven.blocktrain.repo.DataAssetRepository;
import com.sixoneseven.blocktrain.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DataService {

    private static final String BASE_PATH = "D:/BlockChain/blockTrain/data_storage/";

    @Autowired
    private DataAssetRepository repository;

    @Autowired
    private FabricClient fabricClient;

    public DataAsset upload(MultipartFile file, String dataName, String dataType) throws Exception {
        // 1️⃣ 确保本地存储目录存在
        File dir = new File(BASE_PATH);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("无法创建存储目录: " + BASE_PATH);
        }

        // 2️⃣ 构建文件路径
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(dir, filename);

        // 确保父目录存在
        if (!dest.getParentFile().exists() && !dest.getParentFile().mkdirs()) {
            throw new IOException("无法创建文件父目录: " + dest.getParent());
        }

        // 3️⃣ 保存文件
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new IOException("文件保存失败: " + e.getMessage(), e);
        }

        // 4️⃣ 计算 Hash
        String hash = HashUtil.sha256(dest);

        // 5️⃣ 模拟上链，获取 TxID
        String txId = fabricClient.putMetadata(filename, hash);

        // 6️⃣ 保存元数据
        DataAsset asset = new DataAsset();
        asset.setDataName(dataName);
        asset.setDataType(dataType);
        asset.setFilePath(dest.getAbsolutePath());
        asset.setFileHash(hash);
        asset.setTxId(txId);
        asset.setUploadTime(LocalDateTime.now());

        return repository.save(asset);
    }

    public File download(Long id) {
        DataAsset asset = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("数据不存在"));

        File file = new File(asset.getFilePath());
        if (!file.exists()) {
            throw new RuntimeException("文件不存在: " + file.getAbsolutePath());
        }
        return file;
    }
}
