package com.mmall.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gwr0-0 on 2017/10/14.
 */
@Getter
@Setter
public class ShippingVo {

    private String receiverName;            //收货姓名

    private String receiverPhone;           //收货固定电话

    private String receiverMobile;          //收货移动电话

    private String receiverProvince;        //省份

    private String receiverCity;            //城市

    private String receiverDistrict;        //区/县

    private String receiverAddress;         //详细地址

    private String receiverZip;             //邮编

}
