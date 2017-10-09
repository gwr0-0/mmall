package com.mmall.util;

import java.math.BigDecimal;

/**
 * Created by gwr0-0 on 2017/10/3.
 */
public class BigDecimalUtil {

    private BigDecimalUtil() {

    }

    /**
     * 不会丢失精度的两位数加法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    /**
     * 不会丢失精度的两位数减法
     * @param v1
     * @param v2
     * @return v1 - v2
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    /**
     * 不会丢失精度的乘法
     * @param v1
     * @param v2
     * @return v1 * v2
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    /**
     * 不会丢失精度的除法，注意
     * 这里保留两位小数，四舍五入
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }
}
