package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 14:41
 * @Description 聚合（max/min/count)
 */
@Slf4j
public class MaxMinCount {

    private static List<String> groupList1 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
    private static List<Integer> groupList2 = Arrays.asList(7, 6, 9, 4, 11, 6);

    public static void main(String[] args) {
        //获取String集合中最长的元素
        groupList1.stream()
                .max(Comparator.comparing(String::length))
                .get();//最长的字符串：[Optional]

        //获取Integer集合中的最大值
        groupList2.stream()
                .max(Integer::compareTo).get();// 自然排序
        groupList2.stream()
                .max(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
                    }
                }).get();// 自定义排序

        // 获取员工工资最高的人 的工资
        Integer maxSalary = Person.init().stream()
                .max(Comparator.comparingInt(Person::getSalary))
                .map(Person::getSalary)
                .get();
        log.info("getMaxSalary:{}",maxSalary);

        //计算Integer集合中大于6的元素的个数
        long count = groupList2.stream()
                .filter(x -> x > 6)
                .count();
        log.info("Integer集合中大于6的元素的个数:{}",count);
    }
}
