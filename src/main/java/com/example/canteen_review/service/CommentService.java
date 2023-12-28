package com.example.canteen_review.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.canteen_review.entity.po.Comment;
import com.example.canteen_review.entity.vo.CommentVO;

import java.util.List;


public interface CommentService extends IService<Comment> {
    List<CommentVO> listByCanteen(Long canteenId);

    List<CommentVO> listCommentVO(Long dishId, Long userId);
}
