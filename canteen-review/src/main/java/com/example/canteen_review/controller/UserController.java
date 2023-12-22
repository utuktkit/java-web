package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.User;
import com.example.canteen_review.service.UserService;
import com.example.canteen_review.utils.JwtUtil;
import com.example.canteen_review.utils.Md5Util;
import com.example.canteen_review.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.canteen_review.entity.po.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password, Integer type) {

        User userInDb = userService.getByUsername(username);
        if (userInDb != null) {
            return Result.error("用户名已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(Md5Util.getMD5String(password));
        user.setType(Objects.requireNonNullElse(type, 1));

        userService.save(user);

        return Result.success();
    }

    @PostMapping("/login")
    public Result login(String username, String password) {

        User user = userService.getByUsername(username);

        if (user == null) {
            return Result.error("用户名不存在");
        }

        if (!user.getPassword().equals(Md5Util.getMD5String(password))) {
            return Result.error("密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("type", user.getType());
        String token = JwtUtil.genToken(claims);

        return Result.success(token);
    }

    @PostMapping("/updatePwd")
    public Result updatePwd(String oldPwd, String newPwd) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = Long.valueOf(String.valueOf(map.get("id")));

        User user = userService.getById(userId);

        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("旧密码错误");
        }

        user.setPassword(Md5Util.getMD5String(newPwd));
        userService.updateById(user);

        return Result.success();
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(String nickname, String email, String phone) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = Long.valueOf(String.valueOf(map.get("id")));

        User user = new User();
        user.setUserId(userId);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPhone(phone);

        userService.updateById(user);

        return Result.success();
    }

    @GetMapping("/info")
    public Result info(Long userId) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Long loginUserId = Long.valueOf(String.valueOf(map.get("id")));

        User user = new User();
        if (userId == null) {
            user = userService.getById(loginUserId);
        }
        if (userId != null) {
            user = userService.getById(userId);
            if (!loginUserId.equals(userId)) {
                user.setEmail(null);
                user.setPhone(null);
                user.setExp(null);
            }
        }

        return Result.success(user);
    }

    @GetMapping("/test")
    public Result test() {
        return Result.success("test successfully");
    }
}
