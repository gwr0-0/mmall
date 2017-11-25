package com.mmall.pojo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shipping {
    private Integer id;

    private Integer userId;

    private String receiverName;            //收货姓名

    private String receiverPhone;           //收货固定电话

    private String receiverMobile;          //收货移动电话

    private String receiverProvince;        //省份

    private String receiverCity;            //城市

    private String receiverDistrict;        //区/县

    private String receiverAddress;         //详细地址

    private String receiverZip;             //邮编

    private Date createTime;

    private Date updateTime;

}