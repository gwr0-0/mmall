package com.mmall.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by gwr0-0 on 2017/9/28.
 */
@Getter
@Setter
public class ProductDetailVo {

    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private String subImages;
    private String detail; //富文本
    private BigDecimal price; //价格，避免丢失精度
    private Integer stock; //库存
    private Integer status;
    private String createTime;
    private String updateTime;
    private String imageHost; //图片服务器前缀
    private Integer parentCategoryId;

}
