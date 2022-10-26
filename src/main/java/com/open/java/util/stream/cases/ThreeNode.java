package com.open.java.util.stream.cases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年10月24日 20:20
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreeNode {

    private Integer id;
    private Integer parentId;
    private String name;
    private List<ThreeNode> childNode;
    private Integer childNodeSize;

    public ThreeNode(Integer id, String name,Integer parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
