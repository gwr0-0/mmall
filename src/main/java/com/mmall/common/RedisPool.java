package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author gwr0-0
 * @date 2017/11/30
 */
public class RedisPool {

    //jedis连接池
    private static JedisPool pool;

    private static String ip = PropertiesUtil.getProperty("redis.ip");
    private static Integer port = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));

    //最多连接数
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.maxTotal", "20"));
    //在jedispool中最大的idle状态（空闲的）的jedis实例的个数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.maxIdle", "10"));
    //在jedispool中最小的idle状态（空闲的）的jedis实例的个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.minIdle", "2"));

    private static Integer maxWait = Integer.parseInt(PropertiesUtil.getProperty("redis.maxWait", "1000"));
    //在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true，则得到的jedis实例肯定是可以用的
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnBorrow", "true"));
    //在return一个jedis实例的时候，是否要进行验证操作，如果赋值true，则放回jedispool的jedis实例肯定是可以用的
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnReturn", "true"));
    public static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        pool = new JedisPool(config, ip, port, 1000*2);

    }

    static {
        initPool();
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("a", "a");
        jedis.set("aa", "aa");
        returnResource(jedis);

        pool.destroy();//临时调用，销毁连接池中所有连接
        System.out.println("END");
    }

}
