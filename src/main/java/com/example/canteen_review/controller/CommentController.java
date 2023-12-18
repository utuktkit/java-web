package com.example.canteen_review.controller;

import com.example.canteen_review.entity.po.Comment;
import com.example.canteen_review.entity.po.Result;
import com.example.canteen_review.service.CommentService;
import com.example.canteen_review.service.DishService;
import com.example.canteen_review.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {
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

        return Result.success();
    }
}
