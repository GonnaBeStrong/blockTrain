package com.sixoneseven.blocktrain.service;

import com.sixoneseven.blocktrain.dto.CredentialDTO;
import com.sixoneseven.blocktrain.fabric.FabricClient;
import com.sixoneseven.blocktrain.repo.entity.DataAsset;
import com.sixoneseven.blocktrain.repo.DataAssetRepository;
import com.sixoneseven.blocktrain.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CredentialService {

    @Autowired
    private DataAssetRepository repository;

    @Autowired
    private FabricClient fabricClient;

    public Map<String, Object> getCredential(Long dataId) throws Exception {

        DataAsset asset = repository.findById(dataId)
                .orElseThrow(() -> new RuntimeException("数据不存在"));

        // ① 本地重新计算 Hash（最严谨）
        File file = new File(asset.getFilePath());
        String localHash = HashUtil.sha256(file);

        // ② 查询链上 Hash
        String chainHash = fabricClient.queryMetadata(asset.getFilePath());

        // ③ 对比
        boolean verified = localHash.equals(chainHash);

        // ④ 返回可信凭证
        Map<String, Object> result = new HashMap<>();
        result.put("dataId", dataId);
        result.put("fileHash", localHash);
        result.put("chainHash", chainHash);
        result.put("txId", asset.getTxId());
        result.put("verified", verified);
        result.put("verifyTime", LocalDateTime.now());

        return result;
    }
}
