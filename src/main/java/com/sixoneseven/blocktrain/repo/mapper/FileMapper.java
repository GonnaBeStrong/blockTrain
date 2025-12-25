package com.sixoneseven.blocktrain.repo.mapper;

import com.sixoneseven.blocktrain.repo.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {

    public File getFileByDataId(@Param("dataId")Integer dataId);

}
