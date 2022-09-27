package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 15:47
 * @Description
 * sorted()：自然排序，流中元素需实现Comparable接口
 * sorted(Comparator com)：Comparator排序器自定义排序
 */
@Slf4j
public class Sorted {
    public static void main(String[] args) {
        List<Person> personList = Person.init();
        //按工资升序排序（自然排序）
        List<String> newList = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary))
                .map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary)
                        .thenComparing(Person::getAge))
                .map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream()
                .sorted((p1, p2) -> {
                    if (p1.getSalary() == p2.getSalary()) {
                        return p2.getAge() - p1.getAge();
                    } else {
                        return p2.getSalary() - p1.getSalary();
                    }
                }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }
}
