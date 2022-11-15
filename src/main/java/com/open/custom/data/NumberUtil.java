package com.open.custom.data;

import java.math.BigDecimal;

/**
 * @author liuxiaowei
 * @date 2022年10月27日 13:42
 * @Description hutool.NumberUtil扩展
 */
public class NumberUtil extends cn.hutool.core.util.NumberUtil {

    public static final Integer OPEN = 1;

    public static final Integer ZERO = 0;

    public static boolean equal(Number v1, Number v2) {
        return v1 != null && v1.equals(v2);
    }

    public static boolean notEqual(Number v1, Number v2) {
        return !equal(v1, v2);
    }

    public static boolean equalOpen(Number v1) {
        return OPEN.equals(v1);
    }

    public static boolean equalClose(Number v1) {
        return !OPEN.equals(v1);
    }

    public static boolean gtZero(Number v1) {
        return v1 != null && v1.longValue() > ZERO;
    }

    public static boolean leZero(Number v1) {
        return v1 != null && v1.longValue() <= ZERO;
    }

    public static boolean gtZero(BigDecimal v1) {
        return v1 != null && v1.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean equalsAll(Number v1, Number... numbers) {
        if (v1 == null || numbers.length == 0) {
            return false;
        }
        for (Number number : numbers) {
            if (!v1.equals(number)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllNull(Number... numbers) {
        for (Number number : numbers) {
            if (number != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasNull(Number... numbers) {
        for (Number number : numbers) {
            if (number == null) {
                return true;
            }
        }
        return false;
    }
}
