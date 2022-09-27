package com.open.guava.eventbus.cases;

import com.google.common.eventbus.EventBus;
import com.open.guava.eventbus.EventListener;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 0:57
 * @Description
 */
public class TestEventBus {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());
    }
}
