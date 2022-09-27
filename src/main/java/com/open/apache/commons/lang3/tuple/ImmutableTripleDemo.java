package com.open.apache.commons.lang3.tuple;

import org.apache.commons.lang3.tuple.ImmutableTriple;

/**
 * @author liuxiaowei
 * @date 2022年09月26日 18:00
 * @Description
 */
public class ImmutableTripleDemo {

    public static ImmutableTriple<String, String,String> get() {
        return ImmutableTriple.of("left", "middle","right");
    }

    public static void main(String[] args) {
        ImmutableTriple<String, String, String> triple = get();
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());
    }
}
