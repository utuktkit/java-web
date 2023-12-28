package com.example.canteen_review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canteen_review.entity.po.Comment;
import com.example.canteen_review.entity.vo.CommentVO;
import com.example.canteen_review.mapper.CommentMapper;
import com.example.canteen_review.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Override
    public List<CommentVO> listByCanteen(Long canteenId) {
        return baseMapper.listByCanteen(canteenId);
    }

    @Override
    public List<CommentVO> listCommentVO(Long dishId, Long userId) {
        return baseMapper.listCommentVO(dishId, userId);
    }
}




