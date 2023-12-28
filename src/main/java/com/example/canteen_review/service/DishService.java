package com.example.canteen_review.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canteen_review.entity.po.Dish;
import com.example.canteen_review.entity.vo.CommentVO;

import java.util.List;

public interface DishService extends IService<Dish> {

    List<Dish> listDish(String name, Long canteenId, Boolean isPriceAsc, Boolean isRatingAsc);

    Dish getByCanteenIdAndName(Long canteenId, String name);

    void updateRating(Long dishId);

    void removeByCanteenId(Long canteenId);
}
