package com.open.java.util.stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.open.java.util.stream.cases.Data1;
import com.open.java.util.stream.cases.Data2;
import com.open.java.util.stream.cases.OutputData;
import com.open.java.util.stream.cases.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;
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

    public static void main(String[] args) {

    }

    private static void testFlatMap() {
        //数据准备
        BookObj book1 = new BookObj("1", "<金刚葫芦娃>", 10.5,new BigDecimal(1));
        BookObj book2 = new BookObj("1", "<铁臂阿童木>", 32.5,new BigDecimal(1));
        BookObj book3 = new BookObj("1", "<阿凡提>", 19.5,new BigDecimal(1));

        BookObj book4 = new BookObj("2", "<哈哈>", 19.5,new BigDecimal(1));
        BookObj book5 = new BookObj("2", "<呵呵>", 19.5,new BigDecimal(1));

        //第一个订单一共买了3本书
        List<BookObj> bookObjs1 = Lists.newArrayList();
        bookObjs1.add(book1);
        bookObjs1.add(book2);
        bookObjs1.add(book3);

        //第二个订单一共买了2本书
        List<BookObj> bookObjs2 = Lists.newArrayList();
        bookObjs2.add(book4);
        bookObjs2.add(book5);

        // 假设你从别的小伙伴的接口那里获取到了张三每个订单和订单对应的book List信息Map
        Map<String, List<BookObj>> orderInfoMap = Maps.newHashMap();
        // 第一个订单书的集合
        orderInfoMap.put("1", bookObjs1);
        // 第二个订单书的集合
        orderInfoMap.put("2", bookObjs2);

        // 需求1:产品经理让你获取所有书本的名字,用flatMap可以如下
        List<String> bookNameList  = orderInfoMap.values().stream()
                .flatMap(Collection::stream)
                .map(BookObj::getBookName)
                .collect(Collectors.toList());
        System.out.println("书本的名称:" + bookNameList);

        // 需求2: 获取张三买的每一本书的价格,并排序
        List<Double> bookPriceList  = orderInfoMap.values().stream()
                .flatMap(Collection::stream)
                .map(BookObj::getBookPrice)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("书本的价格:" + bookPriceList);

        // 需求3: 张三2个订单一共花了多少钱?
        Optional<Double> sumPrice  = orderInfoMap.values().stream()
                .flatMap(Collection::stream)
                .map(BookObj::getBookPrice)
                .reduce((p1, p2) -> p1 + p2);
        System.out.println("总共花了:" + sumPrice.get());
        // 解法2:
        Double sumPriceNew = orderInfoMap.values().stream()
                .flatMap(Collection::stream)
                .mapToDouble(BookObj::getBookPrice)
                .sum();
        System.out.println("总共花了:" + sumPriceNew);
        // 解法3: (求促销价格)
        BigDecimal sumPriceNewNew = orderInfoMap.values().stream()
                .flatMap(Collection::stream)
                .map(BookObj::getBookRealPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println("总共实际花了:" + sumPriceNewNew);
    }

    private static void mapAndFlatMap() {
        String[] mapArray = {"abcd", "bcdd", "defde", "fTr"};
        List<Integer> mapList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<String> mapStrList = Arrays.asList("m,k,l,a", "1,3,5,7");

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

    /**
     * 实现List<Data1>和List<Data2>根据Id进行连接，将连接结果输出为一个List<OutputData>
     *
     * @date 2022/10/25 13:07
     */
    public static void intersectByKeyTest() {
        // 准备数据
        List<Data2> listOfData2 = new ArrayList<Data2>();
        listOfData2.add(new Data2(10501, "JOE", "Type1"));
        listOfData2.add(new Data2(10603, "SAL", "Type5"));
        listOfData2.add(new Data2(40514, "PETER", "Type4"));
        listOfData2.add(new Data2(59562, "JIM", "Type2"));
        listOfData2.add(new Data2(29415, "BOB", "Type1"));
        listOfData2.add(new Data2(61812, "JOE", "Type9"));
        listOfData2.add(new Data2(98432, "JOE", "Type7"));
        listOfData2.add(new Data2(62556, "JEFF", "Type1"));
        listOfData2.add(new Data2(10599, "TOM", "Type4"));

        List<Data1> listOfData1 = new ArrayList<Data1>();
        listOfData1.add(new Data1(10501, "JOE", 3000000));
        listOfData1.add(new Data1(10603, "SAL", 6225000));
        listOfData1.add(new Data1(40514, "PETER", 2005000));
        listOfData1.add(new Data1(59562, "JIM", 3000000));
        listOfData1.add(new Data1(29415, "BOB", 3000000));

        /*same by key*/
        List<OutputData> result = listOfData1.stream()
                .flatMap(x -> listOfData2.stream()
                        .filter(y -> x.getId() == y.getId())
                        .map(y -> new OutputData(y.getId(), x.getName(), y.getType(), x.getAmount())))
                .collect(Collectors.toList());
        System.out.println(result);

        /*difference by key*/
        List<Data1> data1IntersectResult = listOfData1.stream()
                .filter(data1 -> listOfData2.stream()
                        .map(Data2::getId)
                        .collect(Collectors.toList())
                        .contains(data1.getId()))
                .collect(Collectors.toList());
        System.out.println(data1IntersectResult);


    }

    @Data
    static class OrderObj {
        //订单id
        private String id;
        //订单创建时间
        //订单名称
    }

    @Data
    @AllArgsConstructor
    static class BookObj {
        //书本关联的订单id
        private String orderId;
        //书本名称
        private String bookName;
        //书本价格
        private Double bookPrice;
        private BigDecimal bookRealPrice;
    }

}
