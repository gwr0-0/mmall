package com.mmall.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by gwr0-0 on 2017/10/14.
 */
@Getter
@Setter
public class OrderItemVo {

    private Long orderNo;                   //订单号

    private Integer productId;              //商品id

    private String productName;             //商品名称

    private String productImage;            //商品图片地址

    private BigDecimal currentUnitPrice;    //生成订单时的商品单价，单位是元,保留两位小数

    private Integer quantity;               //商品数量

    private BigDecimal totalPrice;          //商品总价,单位是元,保留两位小数

    private String createTime;

}
