package com.open.guava.eventbus.cases;

import com.google.common.eventbus.EventBus;
import com.open.guava.eventbus.IntegerListener;
import com.open.guava.eventbus.NumberListener;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 1:04
 * @Description
 */
public class TestEventsFromSubclass {

    //如果Listener A监听Event A, 而Event A有一个子类Event B, 此时Listener A将同时接收Event A和B消息

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        IntegerListener integerListener = new IntegerListener();
        NumberListener numberListener = new NumberListener();
        eventBus.register(integerListener);
        eventBus.register(numberListener);

        eventBus.post(new Integer(100));

        System.out.println("integerListener message:"+integerListener.getLastMessage());
        System.out.println("numberListener message:"+numberListener.getLastMessage());

        eventBus.post(new Long(200L));

        System.out.println("integerListener message:"+integerListener.getLastMessage());
        System.out.println("numberListener message:"+numberListener.getLastMessage());
    }
}
