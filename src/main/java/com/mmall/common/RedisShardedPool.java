package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gwr0-0
 * @date 2017/12/7
 */
public class RedisShardedPool {


    //sharded jedis连接池
    private static ShardedJedisPool pool;

    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));
    private static String redis2Ip = PropertiesUtil.getProperty("redis2.ip");
    private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getProperty("redis2.port"));
    private static Integer REDIS_OUTTIME = 1000 * 2; //2s


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

        //pool = new JedisPool(config, ip, port, 1000*2);
        JedisShardInfo info1 = new JedisShardInfo(redis1Ip, redis1Port, REDIS_OUTTIME);
        JedisShardInfo info2 = new JedisShardInfo(redis2Ip, redis2Port, REDIS_OUTTIME);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>();
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);

        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);

    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(ShardedJedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();
        for (int i = 0; i< 10; i++) {
            jedis.set("kye" + i, "value" + i);
        }
        returnResource(jedis);
        //pool.destroy();//临时调用，销毁连接池中所有连接。main中没必要
        System.out.println("END");
    }

}
