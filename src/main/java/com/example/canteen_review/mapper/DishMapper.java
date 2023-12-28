package com.example.canteen_review.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.canteen_review.entity.po.Dish;
import com.example.canteen_review.entity.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    void updateRating(Long dishId);
}




