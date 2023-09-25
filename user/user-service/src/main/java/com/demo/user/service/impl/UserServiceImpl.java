package com.demo.user.service.impl;

import com.demo.user.entity.User;
import com.demo.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;


@Component
@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Long userId) {
        User user = new User();
        user.setUserName("user project returns name");
        return user;
    }
}
