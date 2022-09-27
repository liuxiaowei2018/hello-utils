package com.open.java;

/**
 * @author liuxiaowei
 * @date 2022年09月27日 14:45
 * @Description 位运算
 */
public class BitOperation {

    // 总结:
    // 2 << 1 = 4 = 2*2
    // 2 << 2 = 2 = 2*2*2
    // 2 << n = 2*(2的n次方)
    // m << n = m*(2的n次方)
    // m >> n = m/(2的n次方)

    public static void main(String[] args) {

        // 2是十进制数，并且是int类型的(java中占四个字节)
        // 位运算是基于二进制bit来的，需要将十进制转换为二进制之后再进行运算
        // 十进制2转换成二进制为“00000000 00000000 00000000 00000010”
        // 再将二进制左移一位，高位丢弃，低位补0，所以结果为“00000000 00000000 00000000 00000100”
        // 换算成十进制则为 4
        System.out.println(2 << 1);

        System.out.println(2 >> 1);
        System.out.println(2 >>> 1);
        System.out.println(-2 >> 1);
        System.out.println(-2 << 1);
        System.out.println(-2 >>> 1);
    }
}
