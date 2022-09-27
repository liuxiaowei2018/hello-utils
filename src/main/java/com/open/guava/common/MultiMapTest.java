package com.open.guava.common;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;

/**
 * @author liuxiaowei
 * @date 2022年08月11日 15:12
 * @Description
 */
public class MultiMapTest {

    public static void main(String[] args) {

        // Multimap: key-value  key可以重复，value也可重复
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();

        multimap.put("csc", "1");
        multimap.put("lwl", "1");
        multimap.put("csc", "1");
        multimap.put("lwl", "one");
        System.out.println(multimap.get("csc"));
        System.out.println(multimap.get("lwl"));

        //MultiSet: 无序+可重复   count()方法获取单词的次数  增强了可读性+操作简单
        Multiset<String> set = HashMultiset.create();
        set.add("csc");
        set.add("lwl");
        set.add("csc");
        System.out.println(set.size());
        System.out.println(set.count("csc"));

        ImmutableList<String> strings = ImmutableList.of("1", "2", "3");
        strings.add(1, "2");
        System.out.println(strings);

    }
}
