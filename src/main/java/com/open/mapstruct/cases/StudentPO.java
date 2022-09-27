package com.open.mapstruct.cases;

import lombok.Builder;
import lombok.Data;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 18:03
 * @Description
 */
@Data
@Builder
public class StudentPO {
    private Integer id;
    private String name;
    private Integer age;
    private String className;
}
