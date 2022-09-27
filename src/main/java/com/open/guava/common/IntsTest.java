package com.open.guava.common;

import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年08月18日 15:59
 * @Description Bytes/Shorts/Ints/Longs/Floats/Doubles/Chars/Booleans
 */
public class IntsTest {

    public static void main(String[] args) {
        List<Integer> list = Ints.asList(1, 2, 3, 4, 5);
        String join = Ints.join(",", 1, 2, 3, 4, 5);
        System.out.println(join);

        int[] concat = Ints.concat(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(concat.length);
        System.out.println(Ints.max(concat));
        System.out.println(Ints.min(concat));
        System.out.println(Ints.contains(concat,5));

        int[] ints = Ints.toArray(list);
    }
}
