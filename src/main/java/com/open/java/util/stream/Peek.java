package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 16:12
 * @Description
 * map接收的是一个Function表达式，有返回值；
 * 而peek接收的是Consumer表达式，没有返回值。
 */
@Slf4j
public class Peek {
    public static void main(String[] args) {
        List<Person> personList = Person.init();
        personList.stream()
                .peek(person -> person.setSalary(10000))
                .forEach(System.out::println);
    }
}
