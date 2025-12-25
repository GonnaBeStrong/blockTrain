package com.sixoneseven.blocktrain.service;

import com.sixoneseven.blocktrain.repo.entity.File;
import com.sixoneseven.blocktrain.repo.entity.Order;
import com.sixoneseven.blocktrain.repo.entity.User;
import com.sixoneseven.blocktrain.repo.mapper.FileMapper;
import com.sixoneseven.blocktrain.repo.mapper.OrderMapper;
import com.sixoneseven.blocktrain.repo.mapper.UserMapper;
import com.sixoneseven.blocktrain.request.OrderRequest;
import com.sixoneseven.blocktrain.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    FileMapper fileMapper;

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

    public List<OrderResponse> orders(Integer userId) {
        if(Objects.isNull(userId)) {
            System.out.println("查询订单，用户id为null");
        }

        // 得到订单里的数据id
        List<Order> orders = orderMapper.getOrderByUserId(userId);

        List<OrderResponse> list = orders.stream().map(order -> {
            Integer dataId = order.getDataId();
            File file = fileMapper.getFileByDataId(dataId);
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(dataId);
            orderResponse.setResolution(file.getResolution());
            orderResponse.setForm(file.getForm());
            orderResponse.setFrom(file.getFrom());
            orderResponse.setDetail(file.getDetail());
            orderResponse.setName(file.getName());
            orderResponse.setSize(file.getSize());
            orderResponse.setPath(file.getPath());
            orderResponse.setUploadTime(file.getUploadTime());
            orderResponse.setLongitude(file.getLongitude());
            orderResponse.setLatitude(file.getLatitude());
            orderResponse.setLite(file.getLite());
            orderResponse.setShootTime(file.getShootTime());
            orderResponse.setBuyTime(order.getBuyTime());
            orderResponse.setOrderId(order.getId());
            return orderResponse;
        }).toList();

        return list;
    }

}
