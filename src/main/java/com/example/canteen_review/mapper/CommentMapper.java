package com.example.canteen_review.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.canteen_review.entity.po.Comment;
import com.example.canteen_review.entity.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVO> listByCanteen(Long canteenId);

    List<CommentVO> listCommentVO(Long dishId, Long userId);
}




