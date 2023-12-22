package com.example.canteen_review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canteen_review.entity.po.Canteen;
import com.example.canteen_review.mapper.CanteenMapper;
import com.example.canteen_review.service.CanteenService;
import org.springframework.stereotype.Service;

@Service
public class CanteenServiceImpl extends ServiceImpl<CanteenMapper, Canteen>
        implements CanteenService {

    @Override
    public Canteen getByName(String name) {
        LambdaQueryWrapper<Canteen> wrapper = new LambdaQueryWrapper<Canteen>()
                .eq(Canteen::getName, name);
        return getOne(wrapper);
    }
}




