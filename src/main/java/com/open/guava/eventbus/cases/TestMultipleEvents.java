package com.open.guava.eventbus.cases;

import com.google.common.eventbus.EventBus;
import com.open.guava.eventbus.MultipleListener;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 1:00
 * @Description
 */
public class TestMultipleEvents {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        MultipleListener multiListener = new MultipleListener();

        eventBus.register(multiListener);

        eventBus.post(new Integer(100));
        eventBus.post(new Integer(200));
        eventBus.post(new Integer(300));
        eventBus.post(new Long(800));
        eventBus.post(new Long(800990));
        eventBus.post(new Long(800882934));

        System.out.println("LastInteger:"+multiListener.getLastInteger());
        System.out.println("LastLong:"+multiListener.getLastLong());
    }
}
