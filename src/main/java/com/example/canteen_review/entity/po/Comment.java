package com.example.canteen_review.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="comment")
@Data
@NoArgsConstructor
public class Comment implements Serializable {
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;
    @NotNull(groups = Insert.class)
    private Long userId;
    @NotNull(groups = Insert.class)
    private Long dishId;
    @NotNull(groups = Insert.class)
    private Integer score;
    @NotNull(groups = Insert.class)
    private String content;
    private Integer state;
    private Date createTime;
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public interface Insert {
    }
}