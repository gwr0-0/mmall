package com.mmall.common;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 将token放到本地cache并声明有效期
 * Created by gwr0-0 on 2017/9/22.
 */
public class TokenCache {

    //日志
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    //将token_前缀设为常量
    public static final String TOKEN_PREFIX = "token_";

    /**
     *
     */
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)  //初始化容量
            .maximumSize(10000)    //缓存最大容量，超过则会使用LRU算法清理
            .expireAfterAccess(12, TimeUnit.HOURS)  //有效期12小时
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现
                //当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载
                //如果直接return null，equals时会报空指针异常，修改为String的null
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    public static String getKey(String key) {
        String value;
        try {
            value = localCache.get(key);
            if("null".equals(value)) {
                return null;
            }
        } catch (Exception e) {
            logger.error("localCache get error", e);
        }
        return null;
    }
}
