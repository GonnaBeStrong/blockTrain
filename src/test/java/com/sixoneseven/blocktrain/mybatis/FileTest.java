package com.sixoneseven.blocktrain.mybatis;

import com.sixoneseven.blocktrain.repo.entity.File;
import com.sixoneseven.blocktrain.repo.mapper.FileMapper;
import com.sixoneseven.blocktrain.repo.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FileTest {

    @Autowired
    public FileMapper fileMapper;

    @Test
    public void getDataByIdTest() {
        File fileByDataId = fileMapper.getFileByDataId(1);
        System.out.println(fileByDataId);
    }

    @Test
    public void getAllFileTest() {
        List<File> allFile = fileMapper.getAllFile();
        allFile.forEach(System.out::println);
    }

    @Test
    public void insertOneFileTest() {
        File file = new File();
        file.setDetail("这是测试自增id");
        file.setAssetId("Aa");
        int i = fileMapper.insertOneFile(file);
        System.out.println(i);
        System.out.println(file.getId());
    }

}
