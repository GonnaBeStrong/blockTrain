package com.sixoneseven.blocktrain.service;

import com.sixoneseven.blocktrain.repo.entity.File;
import com.sixoneseven.blocktrain.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void ordersTest() {
        List<OrderResponse> orders = userService.orders(1);
        System.out.println(orders.size());
        orders.forEach(a -> {
            System.out.println(a);
        });
    }

}
