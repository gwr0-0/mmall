package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * Created by gwr0-0 on 2017/9/25.
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

}
