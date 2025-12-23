package com.sixoneseven.blocktrain.repo;

import com.sixoneseven.blocktrain.entity.DataAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataAssetRepository
        extends JpaRepository<DataAsset, Long> {
}

