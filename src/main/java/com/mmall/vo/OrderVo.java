package com.mmall.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by gwr0-0 on 2017/10/14.
 */
@Getter
@Setter
public class OrderVo {

    private Long orderNo;           //订单号

    private BigDecimal payment;     //总金额

    private Integer paymentType;    //支付方式

    private String paymentTypeDesc; //支付方式描述

    private Integer postage;        //运费

    private Integer status;         //订单状态

    private String statusDesc;      //订单状态描述

    private String paymentTime;       //支付时间

    private String sendTime;

    private String endTime;

    private String closeTime;

    private String createTime;

    //订单明细
    private List<OrderItemVo> orderItemVoList;

    private String imageHost;

    private Integer shippingId;     //收货信息Id

    private String receiverName;    //收货人姓名

    private ShippingVo shippingVo;

}
