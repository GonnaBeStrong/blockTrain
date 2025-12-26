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

import java.sql.Timestamp;
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

    public List<File> allFiles() {
        return fileMapper.getAllFile();
    }

    // 插入数据库成功返回true，失败返回false
    // 插入前请填充字段（除id字段，此字段数据库自动生成）
    public Boolean insertOneFile(File file) {
        int i = fileMapper.insertOneFile(file);
        return i == 1;
    }


    // todo 用户点击购买逻辑
    // 点击按钮，发送userId，dataId，后端在order表记录
    public boolean buy(Integer userId, Integer dataId) {

        List<Order> orders = orderMapper.getOrderByUserId(userId);
        List<Integer> list = orders.stream().map(Order::getDataId).toList();

        if(Objects.isNull(list) || list.contains(dataId)) {
            return false;
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setDataId(dataId);
        order.setBuyTime(new Timestamp(System.currentTimeMillis()));
        int i = orderMapper.insertOrder(order);

        return i == 1;
    }

}
