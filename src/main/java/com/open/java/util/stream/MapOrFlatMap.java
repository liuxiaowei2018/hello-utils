package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 14:52
 * @Description 映射(map / flatMap)
 * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
 * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
 */
@Slf4j
public class MapOrFlatMap {

    private static String[] mapArray = {"abcd", "bcdd", "defde", "fTr"};
    private static List<Integer> mapList = Arrays.asList(1, 3, 5, 7, 9, 11);
    private static List<String> mapStrList = Arrays.asList("m,k,l,a", "1,3,5,7");

    public static void main(String[] args) {

        //英文字符串数组的元素全部改为大写
        //整数数组每个元素+3
        Arrays.stream(mapArray)
                .map(String::toUpperCase)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        mapList.stream()
                .map(x -> x + 3)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //将员工的薪资全部增加1000
        List<Person> personList = Person.init();
        List<Person> peronNewList = personList.stream()
                .map(person -> {
                    Person person1 = new Person(person.getName(), 0, 0, null, null);
                    person1.setSalary(person.getSalary() + 1000);
                    return person1;//[不改变原来员工集合]
                }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + peronNewList.get(0).getName() + "-->" + peronNewList.get(0).getSalary());

        //将两个字符数组合并成一个新的字符数组
        List<String> newStrList = mapStrList.stream()
                .flatMap(str -> {
                    // 将每个元素转换成一个stream
                    String[] split = str.split(",");
                    Stream<String> stream = Arrays.stream(split);
                    return stream;
                }).collect(Collectors.toList());
        System.out.println("处理前的集合：" + mapStrList);
        System.out.println("处理后的集合：" + newStrList);
    }
}
