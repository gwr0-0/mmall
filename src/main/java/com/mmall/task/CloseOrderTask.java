package com.mmall.task;

import com.mmall.common.Const;
import com.mmall.service.IOrderService;
import com.mmall.util.PropertiesUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author gwr0-0
 * @date 2017/12/20
 */
@Component
@Slf4j
public class CloseOrderTask {

    @Autowired
    private IOrderService iOrderService;

//    /**
//     * 单服务器，最简单版
//     */
//    @Scheduled(cron = "0 */1 * * * ?") // 每个1分钟的整数倍
//    public void closeOrderTaskV1() {
//        log.info("关闭订单定时任务启动");
//        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
//        iOrderService.timedCloseOrder(hour);
//        log.info("关闭订单定时任务结束");
//    }

    /**
     * 分布式锁
     */
    @Scheduled(cron = "0 */1 * * * ?") // 每个1分钟的整数倍
    public void closeOrderTaskV2() {
        log.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout", "5000"));
        Long setnxResult = RedisShardedPoolUtil.setNx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            //设置成功，获取锁
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        } else {
            log.info("没有获得分布式锁：{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }
        log.info("关闭订单定时任务结束");
    }

    /**
     * Set a timeout on the lock key.
     * @param lockName
     */
    private void closeOrder(String lockName) {
        int closeLockExpire = Integer.parseInt(PropertiesUtil.getProperty("close.lock.expire", "5"));
        RedisShardedPoolUtil.expire(lockName, closeLockExpire);
        log.info("获取{}, ThreadName:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread());
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
        iOrderService.timedCloseOrder(hour);
        RedisShardedPoolUtil.del(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        log.info("释放{}, ThreadName:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread());
    }
}
