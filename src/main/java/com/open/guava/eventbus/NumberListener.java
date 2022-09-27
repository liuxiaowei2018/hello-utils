package com.open.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 1:03
 * @Description
 */
public class NumberListener {

    private Number lastMessage;

    @Subscribe
    public void listen(Number integer) {
        lastMessage = integer;
        System.out.println("Message:"+lastMessage);
    }

    public Number getLastMessage() {
        return lastMessage;
    }
}
