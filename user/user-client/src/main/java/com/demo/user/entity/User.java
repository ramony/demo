package com.demo.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long userId;

    private String userName;

}
