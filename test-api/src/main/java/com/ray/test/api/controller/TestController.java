package com.ray.test.api.controller;

import com.demo.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public String get() throws InterruptedException {
        Thread.sleep(new Random().nextLong(2000));
        if (new Random().nextInt(100) > 80) {
            throw new RuntimeException("method throws exception");
        }
        return "success";
    }

}
