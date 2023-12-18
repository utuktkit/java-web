package com.example.canteen_review.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.canteen_review.entity.po.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    void updateRating(Long dishId);
}




