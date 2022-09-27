package com.open.mapstruct.cases;

import lombok.Builder;
import lombok.Data;

/**
 * @Author liuxiaowei
 * @Date 2021/1/27 18:37
 * @Description
 */
@Data
@Builder
public class SchoolPO {
    private String name;
    private String location;
}
