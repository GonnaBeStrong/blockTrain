package com.sixoneseven.blocktrain.fabric;

public interface FabricClient {

    String putMetadata(String assetId, String fileHash) throws Exception;

    String queryMetadata(String assetId) throws Exception;
}

