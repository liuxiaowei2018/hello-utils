package com.open.java.util.stream;

import com.open.java.util.stream.cases.Person;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 15:46
 * @Description 自定义归约[Collectors.reducing]
 */
public class Reducing {
    public static void main(String[] args) {
        List<Person> personList = Person.init();
        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }
}
