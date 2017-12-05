package com.mmall.common;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 将token放到本地cache并声明有效期
 * Created by gwr0-0 on 2017/9/22.
 */
@Slf4j
public class TokenCache {

    //将token_前缀设为常量
    public static final String TOKEN_PREFIX = "token_";
    //这个值实际用于分布式改造的时候，跟此类一致设为 12h
    public static final int TOKEN_EXTIME = 60 * 60 * 12;

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
            return value;
        } catch (Exception e) {
            log.error("localCache get error", e);
        }
        return null;
    }
}
