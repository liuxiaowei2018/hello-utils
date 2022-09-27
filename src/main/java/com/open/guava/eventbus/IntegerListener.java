package com.open.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 1:04
 * @Description
 */
public class IntegerListener {

    private Integer lastMessage;

    @Subscribe
    public void listen(Integer integer) {
        lastMessage = integer;
        System.out.println("Message:"+lastMessage);
    }

    public Integer getLastMessage() {
        return lastMessage;
    }
}
