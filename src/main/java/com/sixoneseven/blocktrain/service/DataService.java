package com.sixoneseven.blocktrain.service;


import com.sixoneseven.blocktrain.entity.DataAsset;
import com.sixoneseven.blocktrain.fabric.FabricClient;
import com.sixoneseven.blocktrain.repo.DataAssetRepository;
import com.sixoneseven.blocktrain.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DataService {

    private static final String BASE_PATH = "data_storage/";

    @Autowired
    private DataAssetRepository repository;

    @Autowired
    private FabricClient fabricClient;

    public DataAsset upload(MultipartFile file, String dataName, String dataType) throws Exception {

        // 1️⃣ 保存文件到本地
        File dir = new File(BASE_PATH);
        if (!dir.exists()) dir.mkdirs();

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(dir, filename);
        file.transferTo(dest);

        // 2️⃣ 计算 Hash
        String hash = HashUtil.sha256(dest);

        // 3️⃣ 模拟上链，获取 TxID
        String txId = fabricClient.putMetadata(filename, hash);

        // 4️⃣ 保存元数据
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

        return new File(asset.getFilePath());
    }
}

