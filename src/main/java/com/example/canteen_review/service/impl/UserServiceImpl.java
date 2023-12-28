package com.example.canteen_review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canteen_review.entity.po.User;
import com.example.canteen_review.mapper.UserMapper;
import com.example.canteen_review.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);

        return getOne(wrapper);
    }

    @Override
    public User getByName(String name) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(name != null, User::getUsername, name);

        return getOne(wrapper);
    }

    @Override
    public List<User> listUser(Integer type, String name) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(type != null, User::getType, type)
                .like(name != null, User::getUsername, name);

        return list(wrapper);
    }
}




