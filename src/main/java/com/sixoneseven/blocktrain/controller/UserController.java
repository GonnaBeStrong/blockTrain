package com.sixoneseven.blocktrain.controller;

import com.sixoneseven.blocktrain.dto.CommonResp;
import com.sixoneseven.blocktrain.request.LoginRequest;
import com.sixoneseven.blocktrain.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

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

}
