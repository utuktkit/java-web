package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.CanteenAdmin;
import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.entity.po.User;
import com.example.canteen_review.service.CanteenAdminService;
import com.example.canteen_review.service.CanteenService;
import com.example.canteen_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canteenAdmin")
@RequiredArgsConstructor
@CrossOrigin
public class CanteenAdminController {
    private final CanteenAdminService canteenAdminService;
    private final UserService userService;
    private final CanteenService canteenService;

    // todo check admin 3
    @PostMapping("/addCanteenAdmin")
    public Result addCanteenAdmin(Long canteenId, Long userId) {

        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (user.getType() < 2) {
            return Result.error("用户权限不足");
        }

        CanteenAdmin canteenAdmin = new CanteenAdmin();
        canteenAdmin.setCanteenId(canteenId);
        canteenAdmin.setUserId(userId);

        canteenAdminService.save(canteenAdmin);

        return Result.success();
    }

    // todo check admin 3
    @DeleteMapping("/deleteCanteenAdmin")
    public Result deleteCanteenAdmin(Long canteenId, Long userId) {

        if (userService.getById(userId) == null) {
            return Result.error("用户不存在");
        }

        if (canteenService.getById(canteenId) == null) {
            return Result.error("餐厅不存在");
        }

        canteenAdminService.removeById(canteenAdminService.getByCanteenIdAndUserId(canteenId, userId));

        return Result.success();
    }

    @GetMapping("/ListByCanteenId")
    public Result ListByCanteenId(Long canteenId) {
        return Result.success(canteenAdminService.listByCanteenId(canteenId));
    }
}
