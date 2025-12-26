package com.sixoneseven.blocktrain.repo.entity;

public class DownloadResult {
    private boolean trusted;
    private java.io.File zipFile; // 校验成功时才有值

    public DownloadResult(boolean trusted, java.io.File zipFile) {
        this.trusted = trusted;
        this.zipFile = zipFile;
    }

    public boolean isTrusted() { return trusted; }
    public java.io.File getZipFile() { return zipFile; }
}
