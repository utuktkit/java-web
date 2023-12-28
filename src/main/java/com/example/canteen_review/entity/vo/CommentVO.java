package com.example.canteen_review.entity.vo;

import com.example.canteen_review.entity.po.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentVO {
    private Long commentId;
    private Long userId;
    private Long dishId;
    private Integer score;
    private String content;
    private Integer state;
    private Date createTime;
    private Date updateTime;

    private String dishName;
    private String username;
}
