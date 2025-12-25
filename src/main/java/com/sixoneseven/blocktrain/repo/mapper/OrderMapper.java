package com.sixoneseven.blocktrain.repo.mapper;

import com.sixoneseven.blocktrain.repo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    public List<Order> getOrderByUserId(@Param("userId") Integer userId);

}
