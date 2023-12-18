package com.example.canteen_review.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName canteen_admin
 */
@TableName(value ="canteen_admin")
@Data
public class CanteenAdmin implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long canteenAdminId;
    private Long canteenId;
    private Long userId;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}