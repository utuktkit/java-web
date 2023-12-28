package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.Dish;
import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.service.CanteenService;
import com.example.canteen_review.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
@CrossOrigin
public class DishController {
    private final DishService dishService;
    private final CanteenService canteenService;

    // todo check admin 2
    @PostMapping("/addDish")
    public Result addDish(@RequestBody @Validated(Dish.Insert.class) Dish dish) {

        if (dishService.getByCanteenIdAndName(dish.getCanteenId(), dish.getName()) != null) {
            return Result.error("菜品名称重复");
        }

        dishService.save(dish);

        return Result.success();
    }

    @PutMapping("/updateDish")
    public Result updateDish(@RequestBody @Validated(Dish.Update.class) Dish dish) {

        if (dishService.getByCanteenIdAndName(dish.getCanteenId(), dish.getName()) != null) {
            return Result.error("菜品名称重复");
        }

        dish.setRate(null);
        dish.setRatingPeople(null);

        dishService.updateById(dish);

        return Result.success();
    }

    @GetMapping("/getDish")
    public Result getDish(Long dishId) {

        Dish dish = dishService.getById(dishId);

        if (dish == null) {
            return Result.error("菜品不存在");
        }

        return Result.success(dish);
    }

    @GetMapping("/listDish")
    public Result listDish(String name, Long canteenId, Boolean isPriceAsc, Boolean isRatingAsc) {
        if (canteenId != null && canteenService.getById(canteenId) == null) {
            return Result.error("餐厅不存在");
        }

        return Result.success(dishService.listDish(name, canteenId, isPriceAsc, isRatingAsc));
    }
}
