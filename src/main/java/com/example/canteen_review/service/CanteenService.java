package com.example.canteen_review.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canteen_review.entity.po.Canteen;

public interface CanteenService extends IService<Canteen> {

    Canteen getByName(String name);
}
