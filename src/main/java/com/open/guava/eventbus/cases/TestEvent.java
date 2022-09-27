package com.open.guava.eventbus.cases;

/**
 * @Author liuxiaowei
 * @Date 2021/5/23 0:56
 * @Description
 */
public class TestEvent {

    private final Integer message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }
    public int getMessage() {
        return message;
    }
}
