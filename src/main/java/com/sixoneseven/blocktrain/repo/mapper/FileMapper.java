package com.sixoneseven.blocktrain.repo.mapper;

import com.sixoneseven.blocktrain.repo.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    public File getFileByDataId(@Param("dataId")Integer dataId);

    public List<File> getAllFile();

}
