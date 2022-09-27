package com.open.guava.common;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 * @author liuxiaowei
 * @date 2022年08月18日 16:24
 * @Description 通过guava对JDK提供的线程池进行装饰，让其具有异步回调监听功能，然后在设置监听器
 */
public class MoreExecutorsTest {

    public static void main(String[] args) {

        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture future = executor.submit(() -> {
            if (new Random().nextInt(3) == 2) {
                throw new NullPointerException();
            }
            return 1;
        });
        FutureCallback<Integer> callback = new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer obj) {
                System.out.println("------" + obj);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("------" + throwable.getMessage());
            }
        };
        Futures.addCallback(future, callback, executor);
    }
}
