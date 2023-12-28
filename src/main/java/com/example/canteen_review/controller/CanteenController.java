package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.Canteen;
import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.service.CanteenAdminService;
import com.example.canteen_review.service.CanteenService;
import com.example.canteen_review.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canteen")
@RequiredArgsConstructor
@CrossOrigin
public class CanteenController {
    private final CanteenService canteenService;
    private final DishService dishService;
    private final CanteenAdminService canteenAdminService;

    // todo check admin 3
    @PostMapping("/addCanteen")
    public Result addCanteen(@RequestBody @Validated(Canteen.Insert.class) Canteen canteen) {

        if (canteenService.getByName(canteen.getName()) != null) {
            return Result.error("餐厅名称重复");
        }

        canteenService.save(canteen);
        return Result.success();
    }

    // todo check admin 3
    @PutMapping("/updateCanteen")
    public Result updateCanteen(@RequestBody @Validated(Canteen.Update.class) Canteen canteen) {

        Canteen canteenInDb = canteenService.getById(canteen.getCanteenId());

        if (canteenInDb != null && !canteenInDb.getCanteenId().equals(canteen.getCanteenId())) {
            return Result.error("餐厅名称重复");
        }

        canteenService.updateById(canteen);
        return Result.success();
    }

    @GetMapping("/getCanteen")
    public Result getCanteenByName(Long canteenId) {
        Canteen canteen = canteenService.getById(canteenId);
        return Result.success(canteen);
    }

    @GetMapping("/listCanteen")
    public Result listCanteen(String name) {
        return Result.success(canteenService.listCanteenByName(name));
    }

    @Transactional
    @DeleteMapping("/deleteCanteen")
    public Result deleteCanteen(Long canteenId) {

        if (canteenId == null) {
            return Result.error("食堂ID不能为空");
        }

        dishService.removeByCanteenId(canteenId);
        canteenAdminService.removeByCanteenId(canteenId);

        canteenService.removeById(canteenId);

        return Result.success();
    }
}
