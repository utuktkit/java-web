package com.example.canteen_review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canteen_review.entity.po.Dish;
import com.example.canteen_review.mapper.DishMapper;
import com.example.canteen_review.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
        implements DishService {

    @Override
    public List<Dish> listDish(String name, Long canteenId, Boolean isPriceAsc, Boolean isRatingAsc) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<Dish>()
                .like(name != null, Dish::getName, name)
                .eq(canteenId != null, Dish::getCanteenId, canteenId)
                .orderByAsc(isPriceAsc != null && isPriceAsc, Dish::getPrice)
                .orderByDesc(isPriceAsc != null && !isPriceAsc, Dish::getPrice)
                .orderByAsc(isRatingAsc != null && isRatingAsc, Dish::getRatingPeople)
                .orderByDesc(isRatingAsc != null && !isRatingAsc, Dish::getRatingPeople);

        return list(queryWrapper);
    }

    @Override
    public Dish getByCanteenIdAndName(Long canteenId, String name) {
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<Dish>()
                .eq(Dish::getCanteenId, canteenId)
                .eq(Dish::getName, name);

        return getOne(wrapper);
    }

    @Override
    public void updateRating(Long dishId) {
        baseMapper.updateRating(dishId);
    }
}




