package com.example.canteen_review.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "canteen")
@Data
@NoArgsConstructor
public class Canteen implements Serializable {
    @TableId(value = "canteen_id", type = IdType.AUTO)
    @NotNull(groups = Update.class)
    private Long canteenId;
    @NotNull(groups = Insert.class)
    private String name;
    @NotNull(groups = Insert.class)
    private String address;
    @NotNull(groups = Insert.class)
    private String phone;
    private String avatar;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public interface Insert {
    }

    public interface Update {
    }
}