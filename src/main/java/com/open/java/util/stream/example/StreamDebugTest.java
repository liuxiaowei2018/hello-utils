package com.open.java.util.stream.example;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liuxiaowei
 * @date 2022年10月25日 13:16
 * @Description
 */
public class StreamDebugTest {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("C", "D", "A", "B", null)
                .stream()
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());
        int debug = 1 + 10;
        System.out.println(strings);
    }
}
