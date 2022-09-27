package com.open.java.util.stream;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 14:00
 * @Description
 */
@Slf4j
public class StreamCreate {

    public static void main(String[] args) throws FileNotFoundException {
        //顺序流和并行流
        Stream<String> stream0001 = Arrays.asList("1", "2", "3").stream();
        Stream<String> stream0002 = Arrays.asList("1", "2", "3").parallelStream();

        IntStream streamArray001 = Arrays.stream(new int[]{1, 2, 3});
        Stream<Integer> ofStream = Stream.of(1, 2, 3);

        //特殊构建
        Stream.iterate(0, x -> x + 1)
                .limit(3)
                .forEach(System.out::println);
        Stream.generate(Math::random)
                .limit(3)
                .forEach(System.out::println);

        //顺序流转并行流
        Optional<Integer> getFirst = Arrays.asList(1, 2, 3).stream()
                .parallel()
                .filter(x -> x > 2)
                .findFirst();
        if (getFirst.isPresent()) {
            log.info("getFirst:{}",getFirst.get());
        }

        //BufferedReader.lines() 方法，将每行内容转成流
        BufferedReader reader = new BufferedReader(new FileReader("static/test_stream.txt"));
        Stream<String> lineStream  = reader.lines();
        lineStream.forEach(System.out::println);
        //使用 Pattern.splitAsStream() 方法，将字符串分隔成流
        Pattern pattern  = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }
}
