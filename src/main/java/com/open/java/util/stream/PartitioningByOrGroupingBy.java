package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 15:35
 * @Description
 * 分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
 * 分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
 */
@Slf4j
public class PartitioningByOrGroupingBy {

    public static void main(String[] args) {
        testGroupBy02();
        //testGroupBy01();
    }

    private static void testGroupBy02() {
        List<Person> personList = Person.init();
        //1.将员工按薪资是否高于8000分为两部分；2.将员工按性别和地区分组
        Map<Boolean, List<Person>> collect = personList.stream()
                // 将员工按薪资是否高于8000分组
                .collect(Collectors.partitioningBy(perosn -> perosn.getSalary() > 8000));
        Map<String, List<Person>> collect1 = personList.stream()
                // 将员工按性别分组
                .collect(Collectors.groupingBy(Person::getSex));

        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> collect2 = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex,
                        Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + collect);
        System.out.println("员工按性别分组情况：" + collect1);
        System.out.println("员工按性别、地区：" + collect2);
    }

    private static void testGroupBy01() {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        System.out.println(result);
    }
}
