package com.example.canteen_review.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "dish")
@Data
@NoArgsConstructor
public class Dish implements Serializable {
    @TableId(value = "dish_id", type = IdType.AUTO)
    @NotNull(groups = Update.class)
    private Long dishId;
    @NotNull(groups = Insert.class)
    private Long canteenId;
    @NotNull(groups = Insert.class)
    private String name;
    @NotNull(groups = Insert.class)
    private String style;
    @NotNull(groups = Insert.class)
    private BigDecimal price;
    private BigDecimal specialPrice;
    @NotNull(groups = Insert.class)
    private String description;
    private String image;
    private Double rate;
    private Integer ratingPeople;
    private Boolean isSpecialPrice;
    private Date createTime;
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public interface Insert {
    }
    public interface Update {
    }
}