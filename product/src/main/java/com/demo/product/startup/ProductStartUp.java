package com.demo.product.startup;

import com.demo.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ProductStartUp implements ApplicationRunner {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ProductStartUp:" + userService.getUserById(0L));
    }
}
