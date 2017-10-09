package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    List<Cart> selectCartByUserId(Integer userId);

    int selectCartProductCheckedStatus(Integer userId);

    int deleteByUserIdProductIds(@Param("userId") Integer userId, @Param("productIdList") List<String> productIdList);

    /**
     * 全选、全反选、单独选、单独反选
     * 使用<if></if>重用该sql
     * @param userId
     * @param checked
     * @param productId  单选对象id
     * @return
     */
    int checkedOrUncheckedProduct(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("checked") Integer checked);

    /**
     * 使用IFNULL解决购物车为空时返回null的问题
     * IFNULL(expr1,expr2)，假如expr1不为NULL，则返回值为expr1，否则返回expr2
     * 这里IFNULL(sum(quantity), 0)，即sum()为空时返回0
     * @param userId
     * @return
     */
    int selectCartProductCount(@Param("userId") Integer userId);
}