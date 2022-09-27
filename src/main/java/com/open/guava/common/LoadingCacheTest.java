package com.open.guava.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

/**
 * @author liuxiaowei
 * @date 2022年08月11日 15:05
 * @Description
 */
public class LoadingCacheTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LoadingCache<Integer, Long> build = CacheBuilder.newBuilder()
                .initialCapacity(10)
                .concurrencyLevel(10)
                .expireAfterAccess(Duration.ofSeconds(10))
                .weakValues()
                .recordStats()
                .removalListener((RemovalListener<Integer, Long>) notification -> System.out.println(notification.getValue()))
                .build(new CacheLoader<Integer, Long>() {
                    @Override
                    public Long load(Integer key) {
                        return System.currentTimeMillis();
                    }
                });
        System.out.println(build.get(1));
        Thread.sleep(1000);
        System.out.println(build.get(1));
    }
}
