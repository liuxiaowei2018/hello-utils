package com.open.guava.common;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuxiaowei
 * @date 2022年09月29日 20:43
 * @Description
 */
public class GuavaRateLimiter {

    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        RateLimiter limiter = RateLimiter.create(2.0);
        ExecutorService es = Executors.newFixedThreadPool(1);
        //记录上一次执行时间
        final long[] prev = {System.nanoTime()};
        //测试执行20次
        for (int i = 0; i < 20; i++) {
            // 限流器限流
            limiter.acquire();
            // 提交任务异步执行
            es.execute(() -> {
                long cur = System.nanoTime();
                // 打印时间间隔：毫秒 (任务提交到线程池的时间间隔基本上稳定在 500 毫秒。)
                System.out.println((cur - prev[0]) / 1000000);
                prev[0] = cur;
            });
        }
    }
}
