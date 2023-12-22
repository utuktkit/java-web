package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.Canteen;
import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.service.CanteenService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canteen")
@RequiredArgsConstructor
@CrossOrigin
public class CanteenController {
    private final CanteenService canteenService;

    // todo check admin 3
    @PostMapping("/addCanteen")
    public Result addCanteen(@RequestBody @Validated(Canteen.Insert.class) Canteen canteen) {
        System.out.println(canteen);

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
}
