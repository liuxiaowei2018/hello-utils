package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 15:23
 * @Description
 * 归集(toList/toSet/toMap)
 * 统计(count/averaging)
 *  计数：count
 *  平均值：averagingInt、averagingLong、averagingDouble
 *  最值：maxBy、minBy
 *  求和：summingInt、summingLong、summingDouble
 *  统计以上所有：summarizingInt、summarizingLong、summarizingDouble
 */
@Slf4j
public class Collect {

    public static void main(String[] args) {
        List<Person> personList = Person.init();
        //统计员工人数、平均工资、工资总额、最高工资
        long count = personList.stream()
                .filter(person -> person.getAge() > 10).count();
        Double collect = personList.stream()
                .collect(Collectors.averagingDouble(Person::getSalary));
        Optional<Integer> max = personList.stream()
                .map(Person::getSalary).max(Integer::compare);
        int sum = personList.stream().mapToInt(Person::getSalary).sum();
        // 一次性统计所有信息
        DoubleSummaryStatistics collect1 = personList.stream()
                .collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工工资所有统计：" + collect1);
    }

}
