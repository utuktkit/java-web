package com.example.canteen_review.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canteen_review.entity.po.User;

public interface UserService extends IService<User> {

    User getByUsername(String username);
}
