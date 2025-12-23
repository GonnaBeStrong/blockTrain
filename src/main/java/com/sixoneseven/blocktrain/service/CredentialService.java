package com.sixoneseven.blocktrain.service;

import com.sixoneseven.blocktrain.entity.CredentialDTO;
import com.sixoneseven.blocktrain.entity.DataAsset;
import com.sixoneseven.blocktrain.repo.DataAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    @Autowired
    private DataAssetRepository repository;

    public CredentialDTO getCredential(Long dataId) {

        DataAsset asset = repository.findById(dataId)
                .orElseThrow(() -> new RuntimeException("数据不存在"));

        CredentialDTO dto = new CredentialDTO();
        dto.setDataId(asset.getId());
        dto.setDataName(asset.getDataName());
        dto.setFileHash(asset.getFileHash());
        dto.setTxId(asset.getTxId());
        dto.setUploadTime(asset.getUploadTime());

        return dto;
    }
}
