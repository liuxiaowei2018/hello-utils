package com.open.java.lang.reflect;

import java.lang.reflect.Field;

/**
 * @author liuxiaowei
 * @date 2022年09月27日 14:44
 * @Description
 */
public class StringDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field value = "abca".getClass().getDeclaredField("value");
        value.setAccessible(true);
        // -- > dbca
        char[] abc = (char[])value.get("abca");
        abc[0] = 'd';
        System.out.println(new String("abca").equals("dbc"));
    }
}
