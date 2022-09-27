package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 15:06
 * @Description 归约(reduce)
 * 实现对集合求和、求乘积和求最值操作
 */
@Slf4j
public class Reduce {
    private static List<Integer> reduceList = Arrays.asList(1, 3, 2, 8, 11, 4);

    public static void main(String[] args) {
        // 求和
        Optional<Integer> sum1 = reduceList.stream()
                .reduce((x, y) -> x + y);
        Optional<Integer> sum2 = reduceList.stream()
                .reduce(Integer::sum);
        Integer sum3 = reduceList.stream()
                .reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = reduceList.stream()
                .reduce((x, y) -> x * y);

        // 求最大值
        Optional<Integer> max1 = reduceList.stream()
                .reduce((x, y) -> x > y ? x : y);
        Integer max2 = reduceList.stream()
                .reduce(1, Integer::max);

        System.out.println("list求和：" + sum1.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求最大值：" + max1.get() + "," + max2);

        //求所有员工的工资之和和最高工资
        List<Person> personList = Person.init();
        //求工资之和
        Optional<Integer> sumSalary1 = personList.stream()
                .map(Person::getSalary)
                .reduce(Integer::sum);
        Integer sumSalary2 = personList.stream()
                .reduce(0,
                        (sum, perosn) -> sum += perosn.getSalary(),
                        (sum01, sum02) -> sum01 + sum02);
        Integer sumSalary3  = personList.stream()
                .reduce(0,
                        (sum, perosn) -> sum += perosn.getSalary(),
                        Integer::sum);
        // 求最高工资
        Integer maxSalary1 = personList.stream()
                .reduce(0, (max, person) -> max > person.getSalary() ? max : person.getSalary(),
                        Integer::max);
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                (max01, max02) -> max01 > max02 ? max01 : max02);
        System.out.println("工资之和：" + sumSalary1.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary1 + "," + maxSalary2);

    }
}
