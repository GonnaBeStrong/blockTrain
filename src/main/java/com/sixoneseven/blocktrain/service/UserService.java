package com.sixoneseven.blocktrain.service;

import com.sixoneseven.blocktrain.repo.entity.User;
import com.sixoneseven.blocktrain.repo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Boolean checkPassWord(Integer id,String password) {
        if(Objects.isNull(id) || Objects.isNull(password)) {
            return false;
        }
        User user = userMapper.getUser(id * 1L);
        if(Objects.isNull(user)) {
            return false;
        }

        boolean equals = password.equals(user.getPassword());
        return equals;
    }

}
