package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.CanteenAdmin;
import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.entity.po.User;
import com.example.canteen_review.service.CanteenAdminService;
import com.example.canteen_review.service.CanteenService;
import com.example.canteen_review.service.UserService;
import com.example.canteen_review.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

        if (canteenAdminService.checkRepetition(canteenId, userId)) {
            return Result.error("重复添加");
        }

        CanteenAdmin canteenAdmin = new CanteenAdmin();
        canteenAdmin.setCanteenId(canteenId);
        canteenAdmin.setUserId(userId);

        canteenAdminService.save(canteenAdmin);

        return Result.success();
    }

    @PostMapping("/addByCanteenName")
    public Result addByCanteenName(String canteenName, Long userId) {

        Long canteenId = canteenService.getByName(canteenName).getCanteenId();

        if (canteenId == null) {
            return Result.error("食堂不存在");
        }

        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }

        user.setType(2);
        userService.updateById(user);

//        if (user.getType() < 2) {
//            return Result.error("用户权限不足");
//        }

        if (canteenAdminService.getByUserId(userId) == null) {
            return Result.error("用户已在管理餐厅");
        }

        if (canteenAdminService.checkRepetition(canteenId, userId)) {
            return Result.error("重复添加");
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

    @GetMapping("/listByCanteenId")
    public Result listByCanteenId(Long canteenId) {
        return Result.success(canteenAdminService.listByCanteenId(canteenId));
    }

    @GetMapping("/getCanteenByAdminId")
    public Result getCanteenByAdminId(Long userId) {
        return Result.success(canteenAdminService.getByUserId(userId));
    }
}
