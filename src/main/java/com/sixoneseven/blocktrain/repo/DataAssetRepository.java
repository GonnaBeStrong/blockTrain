package com.sixoneseven.blocktrain.repo;

import com.sixoneseven.blocktrain.repo.entity.DataAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataAssetRepository
        extends JpaRepository<DataAsset, Long> {
}

