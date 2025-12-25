package com.sixoneseven.blocktrain.mybatis;

import com.sixoneseven.blocktrain.repo.entity.User;
import com.sixoneseven.blocktrain.repo.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void addPath() {
        System.out.println("-----");
        User user = userMapper.getUser(1L);
        System.out.println("--" + user.getPassword());

    }

}
