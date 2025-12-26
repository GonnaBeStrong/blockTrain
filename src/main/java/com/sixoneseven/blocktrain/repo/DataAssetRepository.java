package com.sixoneseven.blocktrain.repo;

import com.sixoneseven.blocktrain.repo.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataAssetRepository extends JpaRepository<File, Long> {
    Optional<File> findByAssetId(String assetId);
}


