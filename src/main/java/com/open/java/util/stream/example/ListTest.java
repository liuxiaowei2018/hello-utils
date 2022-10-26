package com.open.java.util.stream.example;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.open.java.util.stream.cases.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuxiaowei
 * @date 2022年10月25日 12:50
 * @Description
 */
public class ListTest {

    public static void main(String[] args) {
        List<Person> init = Person.init();
        ArrayList<Person> other = Lists.newArrayList();
        other.add(new Person("Tom", 8900, 23, "male", "New York"));
        other.add(new Person("Jack", 7000, 25, "male", "Washington"));
        other.add(new Person("srt", 7800, 21, "female", "Washington"));
        other.add(new Person("sgh", 8200, 24, "female", "New York"));

        // 简单求交集 (Tom Jack)
        System.out.println(init.stream().filter(other::contains).collect(Collectors.toList()));

        // 简单求并集
        System.out.println(Stream.of(init, other).flatMap(Collection::stream).distinct().collect(Collectors.toList()).size());

        // 简单求init差集
        System.out.println(init.stream().filter(x -> !other.contains(x)).collect(Collectors.toList()));

        // 合并list
        HashMap<Integer, List<Person>> map = Maps.newHashMap();
        map.put(1, init);
        map.put(2, other);
        System.out.println(map.values().stream().flatMap(Collection::stream).collect(Collectors.toList()).size());

    }


}
