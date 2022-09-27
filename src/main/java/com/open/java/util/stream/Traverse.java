package com.open.java.util.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 14:20
 * @Description stream遍历/匹配（foreach/find/match）
 */
public class Traverse {

    private static List<Integer> traverseList = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

    public static void main(String[] args) {

        traverseList.stream()
                .filter(x -> x>6)
                .forEach(System.out::println);
        // 匹配第一个[Optional]
        traverseList.stream()
                .filter(x -> x>6)
                .findFirst().get();
        // 匹配任意（适用于并行流）[Optional]
        traverseList.parallelStream()
                .filter(x -> x > 6)
                .findAny().get();
        // 是否包含符合特定条件的元素[true or false]]
        traverseList.stream()
                .anyMatch(x -> x > 6);

    }
}
