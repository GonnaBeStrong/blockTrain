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
    public void getAllFile() {
        List<File> allFile = fileMapper.getAllFile();
        allFile.forEach(System.out::println);
    }

}
