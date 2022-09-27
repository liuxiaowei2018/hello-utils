package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 14:26
 * @Description
 */
@Slf4j
public class Filter {

    private static List<Integer> filterList = Arrays.asList(6, 7, 3, 8, 1, 2, 9);

    public static void main(String[] args) {
        //filterTest();
        List<String> fiterList = Person.init().stream()
                .filter(person -> person.getSalary() > 800)
                .map(Person::getName)
                .collect(Collectors.toList());
        //.forEach(name -> System.out.print(name+" "));
        log.info("getFilterList:薪资高于800的员工姓名{}", fiterList);
    }

    private static void filterTest() {
        filterList.stream()
                .filter(x -> x > 7)
                .forEach(System.out::print);
    }
}
