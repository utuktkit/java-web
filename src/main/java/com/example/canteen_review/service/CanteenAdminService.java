package com.example.canteen_review.service;

import com.example.canteen_review.entity.po.Canteen;
import com.example.canteen_review.entity.po.CanteenAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canteen_review.entity.po.User;

import java.util.List;

public interface CanteenAdminService extends IService<CanteenAdmin> {

    CanteenAdmin getByCanteenIdAndUserId(Long canteenId, Long userId);

    List<User> listByCanteenId(Long canteenId);

    Boolean checkRepetition(Long canteenId, Long userId);

    void removeByCanteenId(Long canteenId);

    Canteen getByUserId(Long userId);
}
