package com.sixoneseven.blocktrain.entity;

import java.time.LocalDateTime;

public class CredentialDTO {

    private Long dataId;
    private String dataName;
    private String fileHash;
    private String txId;
    private LocalDateTime uploadTime;

    // getter / setter
    public Long getDataId() { return dataId; }
    public void setDataId(Long dataId) { this.dataId = dataId; }

    public String getDataName() { return dataName; }
    public void setDataName(String dataName) { this.dataName = dataName; }

    public String getFileHash() { return fileHash; }
    public void setFileHash(String fileHash) { this.fileHash = fileHash; }

    public String getTxId() { return txId; }
    public void setTxId(String txId) { this.txId = txId; }

    public LocalDateTime getUploadTime() { return uploadTime; }
    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }
}
