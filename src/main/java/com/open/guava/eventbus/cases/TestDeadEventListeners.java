package com.open.guava.eventbus.cases;

import com.google.common.eventbus.EventBus;
import com.open.guava.eventbus.DeadEventListener;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 1:02
 * @Description
 */
public class TestDeadEventListeners {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        //如果没有消息订阅者监听消息， EventBus将发送DeadEvent消息，这时我们可以通过log的方式来记录这种状态。

        System.out.println("deadEvent:"+deadEventListener.isNotDelivered());

    }
}
