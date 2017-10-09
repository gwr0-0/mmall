package com.mmall.service;

        import com.mmall.common.ServerResponse;
        import com.mmall.vo.CartVo;

/**
 * Created by gwr0-0 on 2017/10/3.
 */
public interface ICartService {

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId,Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
