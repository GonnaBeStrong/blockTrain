package com.sixoneseven.blocktrain.fabric;

public interface FabricClient {

    /**
     * 将数据摘要上链，返回交易ID
     */
    String putMetadata(String assetId, String hash);

    /**
     * 从链上查询数据摘要
     */
    String queryMetadata(String txId);

}
