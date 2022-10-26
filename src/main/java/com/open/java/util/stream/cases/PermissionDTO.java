package com.open.java.util.stream.cases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年10月17日 11:44
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer permissionId;

    /**
     * 权限类型（1：菜单权限；2：操作权限）
     */
    private Integer permissionType;

    /**
     * 父级id，顶级为0
     */
    private Integer parentId;

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 操作
     */
    private String operate;

    /**
     * 操作编码
     */
    private String code;

    /**
     * 显示名称
     */
    private String displayValue;

    /**
     * 启用状态：0，未启用；1，已启用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 子权限
     */
    private List<PermissionDTO> children;
}
