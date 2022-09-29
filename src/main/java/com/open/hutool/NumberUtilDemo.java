package com.open.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

/**
 * @author liuxiaowei
 * @date 2022年09月29日 14:07
 * @Description
 */
public class NumberUtilDemo {

    public static void main(String[] args) {
        BigDecimal decimal = NumberUtil.add(new BigDecimal(0.1), null, 5);
        System.out.println(decimal);
        Console.log(NumberUtil.decimalFormat("0.00",decimal));
    }
}
