package com.open.guava.common;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @author liuxiaowei
 * @date 2022年08月18日 15:35
 * @Description
 */
public class JoinSplitterTest {

    // 连接器(定义为static final做复用)
    private static final Joiner JOINER = Joiner.on(",").skipNulls();

    // 分割器
    private static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

    // 字符串匹配器
    private static final CharMatcher CHAR_MATCHER = CharMatcher.any();
    private static final CharMatcher CHAR_MATCHER_2 = CharMatcher.none();
    private static final CharMatcher CHAR_MATCHER_3 = CharMatcher.breakingWhitespace();
    private static final CharMatcher CHAR_MATCHER_4 = CharMatcher.whitespace();

    public static void main(String[] args) {

        String join = JOINER.join(Lists.newArrayList("","a", null, "b"));
        System.out.println("join=" + join); // join=,a,b

        SPLITTER.split("a, ,b,,").forEach(System.out::println); // a b
    }
}
