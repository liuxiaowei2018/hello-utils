package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 15:43
 * @Description
 * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
 */
@Slf4j
public class Joining {

    public static void main(String[] args) {
        List<Person> personList = Person.init();
        String names = personList.stream()
                .map(Person::getName)
                .collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }
}
