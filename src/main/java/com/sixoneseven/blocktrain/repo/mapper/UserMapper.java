package com.sixoneseven.blocktrain.repo.mapper;

import com.sixoneseven.blocktrain.repo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public User getUser(@Param("id")Long id);


}
