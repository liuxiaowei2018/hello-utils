package com.open.apache.commons.lang3.tuple;

import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * @author liuxiaowei
 * @date 2022年09月26日 18:02
 * @Description
 */
public class ImmutablePairDemo {

    public static ImmutablePair<String, String> get() {
        return ImmutablePair.of("left", "right");
    }

    public static void main(String[] args) {
        ImmutablePair<String, String> pair = get();
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());
    }
}
