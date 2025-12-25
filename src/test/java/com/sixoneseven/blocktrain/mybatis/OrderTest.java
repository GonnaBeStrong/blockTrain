package com.sixoneseven.blocktrain.mybatis;

import com.sixoneseven.blocktrain.repo.entity.Order;
import com.sixoneseven.blocktrain.repo.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
