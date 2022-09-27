package com.open.java.util.stream.cases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 14:17
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private int salary;
    private int age;
    private String sex;
    private String area;

    public static List<Person> init() {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("Tom", 8900, 23, "male", "New York"));
        list.add(new Person("Jack", 7000, 25, "male", "Washington"));
        list.add(new Person("Lily", 7800, 21, "female", "Washington"));
        list.add(new Person("Anni", 8200, 24, "female", "New York"));
        list.add(new Person("Owen", 9500, 25, "male", "New York"));
        list.add(new Person("Alisa", 7900, 26, "female", "New York"));
        return list;
    }
}
