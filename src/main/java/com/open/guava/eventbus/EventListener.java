package com.open.guava.eventbus;

import com.google.common.eventbus.Subscribe;
import com.open.guava.eventbus.cases.TestEvent;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 0:57
 * @Description 订阅者
 */
public class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }

}
