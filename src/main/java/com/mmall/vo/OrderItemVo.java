package com.mmall.vo;

import java.math.BigDecimal;

/**
 * Created by gwr0-0 on 2017/10/14.
 */
public class OrderItemVo {

    private Long orderNo;                   //订单号

    private Integer productId;              //商品id

    private String productName;             //商品名称

    private String productImage;            //商品图片地址

    private BigDecimal currentUnitPrice;    //生成订单时的商品单价，单位是元,保留两位小数

    private Integer quantity;               //商品数量

    private BigDecimal totalPrice;          //商品总价,单位是元,保留两位小数

    private String createTime;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
