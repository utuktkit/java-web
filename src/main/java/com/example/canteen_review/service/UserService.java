package com.example.canteen_review.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canteen_review.entity.po.User;

import java.util.List;

public interface UserService extends IService<User> {

    User getByUsername(String username);

    User getByName(String name);

    List<User> listUser(Integer type, String name);
}
