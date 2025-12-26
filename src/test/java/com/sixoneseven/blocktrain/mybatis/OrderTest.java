package com.sixoneseven.blocktrain.mybatis;

import com.sixoneseven.blocktrain.repo.entity.Order;
import com.sixoneseven.blocktrain.repo.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void orderBuyUserIdTest() {
        List<Order> orderByUserId = orderMapper.getOrderByUserId(1);
        orderByUserId.stream().forEach(a -> {
            System.out.println(a);
        });
    }

    @Test
    public void insertOrderTest() {
        Order order = new Order();
        order.setUserId(1);
        order.setDataId(4);
        order.setBuyTime(new Timestamp(System.currentTimeMillis()));

        orderMapper.insertOrder(order);
    }

}
