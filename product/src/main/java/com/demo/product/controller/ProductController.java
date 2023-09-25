package com.demo.product.controller;

import com.demo.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductController {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    @GetMapping("/getProduct")
    public String getProduct() {
        return "getProduct-2" + userService.getUserById(0L).getUserName();
    }

}
