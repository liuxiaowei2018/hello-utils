package com.open.guava.common;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author liuxiaowei
 * @date 2022年09月29日 20:11
 * @Description
 */
public class GuavaBloomFilter {

    public static void main(String[] args) {
        // 总数量
        int total = 1000000;

        filter(total);
        filter(total,0.0001);
    }

    private static void filter(int total) {
        // 默认误判率fpp0.03
        // fpp:因为布隆过滤器中总是会存在误判率，因为哈希碰撞是不可能百分百避免的。
        // 布隆过滤器对这种误判率称之为假阳性概率，即：False Positive Probability，简称为fpp。
        BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
        // 初始化 total 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }

        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }

        // (1000309 - 1000000)/(1000000 + 10000) * 100 ≈ 0.030594059405940593
        System.out.println("已匹配数量 " + count);
    }

    private static void filter(int total, double fpp) {
        // 指定误判率：万分之一，提高匹配精度
        BloomFilter<CharSequence> bfWithFpp = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total, fpp);
        for (int i = 0; i < total; i++) {
            bfWithFpp.put("" + i);
        }
        int countFpp = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bfWithFpp.mightContain("" + i)) {
                countFpp++;
            }
        }
        // 误判率 fpp 的值越小，匹配的精度越高。当减少误判率 fpp 的值，需要的存储空间也越大，所以在实际使用过程中需要在误判率和存储空间之间做个权衡。
        // (1000001 - 1000000)/(1000000 + 10000) * 100 ≈ 0.0001
        System.out.println("指定误判率已匹配数量 " + countFpp);
    }
}
