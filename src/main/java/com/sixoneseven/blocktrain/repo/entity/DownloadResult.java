package com.sixoneseven.blocktrain.repo.entity;

public class DownloadResult {
    private boolean trusted;
    private java.io.File zipFile; // 校验成功时才有值
    private String chainHash;
    private String localHash;

    public DownloadResult(boolean trusted, java.io.File zipFile, String chainHash, String localHash) {
        this.trusted = trusted;
        this.zipFile = zipFile;
        this.chainHash = chainHash;
        this.localHash = localHash;
    }

    public boolean isTrusted() { return trusted; }
    public java.io.File getZipFile() { return zipFile; }
    public String getChainHash() {
        return chainHash;
    }
    public String getLocalHash() {
        return localHash;
    }
}
