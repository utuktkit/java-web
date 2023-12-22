package com.example.canteen_review.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="comment_img")
@Data
@NoArgsConstructor
public class CommentImg implements Serializable {
    @TableId(value = "comment_img_id", type = IdType.AUTO)
    private Long commentImgId;
    private Long commentId;
    private String image;
    private Date createTime;
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}