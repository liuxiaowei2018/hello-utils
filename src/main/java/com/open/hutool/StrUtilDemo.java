package com.open.hutool;

import cn.hutool.core.util.StrUtil;

/**
 * @author liuxiaowei
 * @date 2022年09月29日 13:43
 * @Description
 */
public class StrUtilDemo {

    public static void main(String[] args) {
        hasBlackOrEmpty();
        removePrefixOrSuffix();
        sub();
        format();
    }

    /**
     * 格式化字符串
     * 使用{}进行占位即可，然后使用format方法进行格式化
     */
    private static void format() {
        // 使用{}占位
        String template = "{}+{}=2";
        // 1+1=2
        String str1 = StrUtil.format(template, "1", "1");
        System.out.println(str1);
    }

    /**
     * 截取字符串
     * 自动判断越界,支持负数
     */
    private static void sub() {
        String str = "lolly1023";
        String strSub1 = StrUtil.sub(str, 0, 5);
        // lolly
        System.out.println(strSub1);
        String strSub2 = StrUtil.sub(str, 0, -4);
        // lolly
        System.out.println(strSub2);
        String strSub3 = StrUtil.sub(str, 5, 0);
        // lolly
        System.out.println(strSub3);
    }

    /**
     * 删除前后缀(常用于去文件扩展名)
     * removePrefix为删除字符串前缀
     * removeSuffix为删除字符串后缀
     */
    private static void removePrefixOrSuffix() {
        String fileName = StrUtil.removeSuffix("HuTool学习.md", ".md");
        // HuTool学习
        System.out.println(fileName);
        String fileName1 = StrUtil.removePrefix("HuTool学习.md", "HuTool学习.");
        // md
        System.out.println(fileName1);
    }

    /**
     * 判断是否为空
     * hasEmpty只判断是否为null或者是空字符串
     * hasBlank会把不可见的字符也算为空
     */
    private static void hasBlackOrEmpty() {
        String nullStr = null;
        // true
        System.out.println(StrUtil.hasBlank(nullStr));
        // true
        System.out.println(StrUtil.hasEmpty(nullStr));
    }
}
