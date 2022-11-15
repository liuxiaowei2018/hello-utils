package com.open.custom.data;

import com.google.common.collect.Lists;
import com.open.HelloUtilsApplication;
import com.open.java.util.stream.cases.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年11月15日 13:18
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloUtilsApplication.class)
public class StreamUtilTest {

    @Test
    public void testSort() {
        ArrayList<Person> persons = Lists.newArrayList();
        List<Person> people = Person.init();
        // 正排序
        List<Person> sort = StreamUtil.sort(people.stream(), Person::getSalary);
        // 倒排序
        List<Person> sortAndReversed = StreamUtil.sortAndReversed(people.stream(), Person::getSalary);
        String pretty = JsonUtil.obj2JsonPretty(sort);
        String pretty2 = JsonUtil.obj2JsonPretty(sortAndReversed);
        System.out.println(pretty);
        System.out.println(pretty2);
    }


}
