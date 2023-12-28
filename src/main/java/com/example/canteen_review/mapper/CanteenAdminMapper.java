package com.example.canteen_review.mapper;

import com.example.canteen_review.entity.po.Canteen;
import com.example.canteen_review.entity.po.CanteenAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.canteen_review.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CanteenAdminMapper extends BaseMapper<CanteenAdmin> {

    List<User> listByCanteenId(Long canteenId);

    Canteen getByUserId(Long userId);
}




