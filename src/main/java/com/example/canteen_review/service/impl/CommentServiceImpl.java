package com.example.canteen_review.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.canteen_review.entity.po.Comment;
import com.example.canteen_review.mapper.CommentMapper;
import com.example.canteen_review.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

}




