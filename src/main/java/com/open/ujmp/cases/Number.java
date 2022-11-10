package com.open.ujmp.cases;

import lombok.Data;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年11月10日 12:47
 * @Description
 */
@Data
public class Number {

    // 单价
    private BigDecimal price;
    // 数量
    private Integer quantity;

    public static List<Number> getList() {
        List<Number> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Number number = new Number();
            number.setPrice(new BigDecimal(i));
            number.setQuantity(i);
            list.add(number);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Number> list = getList();
        Matrix matrix = DenseMatrix.Factory.zeros(1, list.size());
        for (int i = 0; i < list.size(); i++) {
            Number number = list.get(i);
            matrix.setAsBigDecimal(number.getPrice(), i, 0);
            matrix.setAsInt(number.getQuantity(), 0, i);
        }
        System.out.println(matrix);

        // 转置
        Matrix matrix2 = matrix.transpose();
        System.out.println(matrix2);

        Matrix MtMatrix = matrix.mtimes(matrix2);
        System.out.println(MtMatrix);
    }

}
