package com.open.hutool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author liuxiaowei
 * @date 2022年09月29日 14:14
 * @Description
 */
public class IdUtilDemo {

    public static void main(String[] args) {
        uuid();
        snowflake();

    }

    private static void snowflake() {
        // 参数1为终端ID
        // 参数2为数据中心ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id3 = snowflake.nextId();
        System.out.println(id3);
    }

    private static void uuid() {
        //生成的UUID是带-的字符串
        String uuid = IdUtil.randomUUID();
        System.out.println(uuid);
        //生成的是不带-的字符串
        String simpleUUID = IdUtil.simpleUUID();
        System.out.println(simpleUUID);
    }
}
