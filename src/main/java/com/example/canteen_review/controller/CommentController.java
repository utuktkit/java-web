package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.Comment;
import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.entity.po.User;
import com.example.canteen_review.entity.vo.CommentVO;
import com.example.canteen_review.service.CanteenService;
import com.example.canteen_review.service.CommentService;
import com.example.canteen_review.service.DishService;
import com.example.canteen_review.service.UserService;
import com.example.canteen_review.utils.ExpUtil;
import com.example.canteen_review.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {
    private final UserService userService;
    private final CanteenService canteenService;
    private final CommentService commentService;
    private final DishService dishService;

    @Transactional
    @PostMapping("/addComment")
    public Result addComment(@RequestBody @Validated(Comment.Insert.class) Comment comment) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = Long.valueOf(String.valueOf(map.get("id")));

        comment.setUserId(userId);

        commentService.save(comment);

        dishService.updateRating(comment.getDishId());

        User user = userService.getById(userId);
        Integer exp = user.getExp();

        exp += 10;
        user.setLevel(ExpUtil.updateUserExp(exp));
        user.setExp(exp);

        userService.updateById(user);

        return Result.success();
    }

    @Transactional
    @PostMapping("/updateComment")
    public Result updateComment(@RequestBody @Validated(Comment.Update.class) Comment comment) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = Long.valueOf(String.valueOf(map.get("id")));

        User currentUser = userService.getById(userId);

        Comment commentInDb = commentService.getById(comment.getCommentId());

        if (commentInDb == null) {
            return Result.error("评论不存在");
        }

        if (!Objects.equals(commentInDb.getUserId(), userId) && currentUser.getType() < 3) {
            return Result.error("无权限修改");
        }

        comment.setUserId(userId);
        comment.setDishId(null);
        comment.setState(null);
        comment.setCreateTime(null);

        commentService.updateById(comment);

        dishService.updateRating(commentInDb.getDishId());

        return Result.success();
    }

    @Transactional
    @DeleteMapping("/deleteComment")
    public Result deleteComment(Long commentId) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = Long.valueOf(String.valueOf(map.get("id")));

        Comment commentInDb = commentService.getById(commentId);

        User currentUser = userService.getById(userId);

        if (commentInDb == null) {
            System.out.println("hello");
            return Result.error("评论不存在");
        }

        if (!Objects.equals(commentInDb.getUserId(), userId) && currentUser.getType() < 3) {
            return Result.error("无权限删除");
        }

        commentService.removeById(commentInDb);

        dishService.updateRating(commentInDb.getDishId());

        return Result.success();
    }

    @GetMapping("/listComment")
    public Result listComment(Long dishId, String name, Long userId) {

        Long searchUserId = null;

        if (userId == null) {
            if (name != null) {
                User user = userService.getByName(name);
                if (user == null) {
                    return Result.error("用户不存在");
                }
                searchUserId = user.getUserId();
            }
        } else {
            searchUserId = userId;
        }

        List<CommentVO> list = commentService.listCommentVO(dishId, searchUserId);

        return Result.success(list);
    }

    @GetMapping("/listByCanteen")
    public Result listByCanteen(Long canteenId) {

        if (canteenId != null && canteenService.getById(canteenId) == null) {
            return Result.error("餐厅不存在");
        }

        List<CommentVO> list = commentService.listByCanteen(canteenId);

        return Result.success(list);
    }

}
