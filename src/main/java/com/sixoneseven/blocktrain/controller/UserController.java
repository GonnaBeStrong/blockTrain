package com.sixoneseven.blocktrain.controller;

import com.sixoneseven.blocktrain.dto.CommonResp;
import com.sixoneseven.blocktrain.repo.entity.File;
import com.sixoneseven.blocktrain.repo.entity.Order;
import com.sixoneseven.blocktrain.request.LoginRequest;
import com.sixoneseven.blocktrain.request.OrderRequest;
import com.sixoneseven.blocktrain.response.OrderResponse;
import com.sixoneseven.blocktrain.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public CommonResp<Boolean> download(@RequestBody LoginRequest user) {

        System.out.println("收到登录请求");
        Boolean result = userService.checkPassWord(user.getId(), user.getPassword());
        CommonResp<Boolean> booleanCommonResp = new CommonResp<>(result);
        booleanCommonResp.setSuccess(result);
        return booleanCommonResp;
    }

    @PostMapping("/orders")
    public CommonResp<List<OrderResponse>> orders(@RequestBody OrderRequest user) {

        System.out.println("请求用户所有订单" + "userId" + user.getId());
        List<OrderResponse> orders = userService.orders(user.getId());
        return new CommonResp<>(orders);
    }



}
