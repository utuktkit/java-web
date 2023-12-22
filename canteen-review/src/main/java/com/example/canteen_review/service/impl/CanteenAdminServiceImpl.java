package com.example.canteen_review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canteen_review.entity.po.CanteenAdmin;
import com.example.canteen_review.entity.po.User;
import com.example.canteen_review.service.CanteenAdminService;
import com.example.canteen_review.mapper.CanteenAdminMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanteenAdminServiceImpl extends ServiceImpl<CanteenAdminMapper, CanteenAdmin>
        implements CanteenAdminService {

    @Override
    public CanteenAdmin getByCanteenIdAndUserId(Long canteenId, Long userId) {
        LambdaQueryWrapper<CanteenAdmin> wrapper = new LambdaQueryWrapper<CanteenAdmin>()
                .eq(CanteenAdmin::getCanteenId, canteenId)
                .eq(CanteenAdmin::getUserId, userId);
        return getOne(wrapper);
    }

    @Override
    public List<User> listByCanteenId(Long canteenId) {
        return baseMapper.listByCanteenId(canteenId);
    }
}




