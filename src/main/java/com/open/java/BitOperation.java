package com.open.java;

/**
 * @author liuxiaowei
 * @date 2022年09月27日 14:45
 * @Description 位运算
 *
 * 二进制：前置0b/0B
 * 八进制：前置0
 * 十进制：默认的，无需前置
 * 十六进制：前置0x/0X
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

        //testBinary();
        System.out.println("---------------------------------------");
        //testBinaryChange();
        System.out.println("---------------------------------------");
        //testCpu();
        System.out.println("---------------------------------------");

        changeValue(4, 10);
    }

    /**
     * 判断一个数的奇偶性
     * 原理: 在二进制下偶数的末位肯定是0，奇数的最低位肯定是1
     * 二进制的1它的前31位均为0，所以在和其它数字的前31位与运算后肯定所有位数都是0（无论是1&0还是0&0结果都是0）
     * 唯一区别就是看最低位和1进行与运算的结果：结果为1表示奇数，反则结果为0表示偶数
     * @param n
     * @return boolean
     */
    private static boolean isEvenNum(int n) {
        // 1 -> 0...01（二进制表示）
        return (n & 1) != 1;
    }

    /**
     * 交换两个数的值
     * @param a
     * @param b
     * @return ab交换
     */
    private static void changeValue(int a,int b) {
        System.out.println(a + "xxxxx" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + "xxxxx" + b);
    }


    private static void testCpu() {
        // 2 -> 10
        // 3 -> 11
        // 与 结果：10（二进制数）(相同为1，不同为0,00得0）
        System.out.println("按位与-"+Integer.toBinaryString(2 & 3));

        // 2 -> 10
        // 3 -> 11
        // 或 结果：11（二进制数） （仅需一个是1便是1）
        System.out.println("按位或-"+Integer.toBinaryString(2 | 3));

        // 2 -> 10（其实是00000000000000000000000000000010  共32位）
        // 非后结果：     11111111111111111111111111111101 共32位 (全部的0置为1，1置为0)
        System.out.println("按位非-"+Integer.toBinaryString(~2));

        // 2 -> 10
        // 3 -> 11
        // 异或 结果：01（二进制数）（相同为0，不同为1）
        System.out.println("异或-"+Integer.toBinaryString(2 ^ 3));

        // 2 -> 10
        // 左移3位结果：10000（二进制数）
        System.out.println("左移-"+Integer.toBinaryString(2 << 3));

        // 2 -> 10
        // 右移3位结果：0（二进制数）
        // 位数不够全被移没了，所以最终打印0
        System.out.println("右移-"+Integer.toBinaryString(2 >> 3));

        // 100 -> 1100100
        // 右移3位结果：1100
        System.out.println("右移-"+Integer.toBinaryString(100 >> 3));

        // 2 -> 10
        // 3 -> 11
        // 与后结果：10（二进制数）
        int i = 2;
        i &= 3; // 此效果同 i = i & 3
        System.out.println(Integer.toBinaryString(i)); //打印：10
    }

    private static void testBinaryChange() {
        System.out.println("---------------------------------");
        System.out.println("十进制转二进制：" + Integer.toBinaryString(10));
        System.out.println("十进制转八进制：" + Integer.toOctalString(10));
        System.out.println("十进制转十六进制：" + Integer.toHexString(10));
        System.out.println("---------------------------------------");

        System.out.println("---------------------------------------");

        System.out.println("二进制转十进制：" + Integer.valueOf("11000000", 2).toString());
        System.out.println("八进制转十进制：" + Integer.valueOf("300", 8).toString());
        System.out.println("十六进制转十进制：" + Integer.valueOf("c0", 16).toString());


        System.out.println("---------------------------------------");

        System.out.println(Long.toBinaryString(Long.MAX_VALUE));
        System.out.println(Long.toBinaryString(Long.MAX_VALUE).length()); //63

        System.out.println("---------------------------------------");

        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).length());
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE).length());
    }

    private static void testBinary() {
        int a = 11, b = 3;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(a+b));

        System.out.println("---------------------------------------");

        int i = 0b101;
        //二进制
        System.out.println(i);
        //八进制
        i = 0101;
        System.out.println(i);
        //十进制
        i = 101;
        System.out.println(i);
        //十六进制
        i = 0x101;
        System.out.println(i);

    }
}
