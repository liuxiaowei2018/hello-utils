package com.open.java.util.stream.example;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.open.java.util.stream.cases.PermissionDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuxiaowei
 * @date 2022年10月17日 11:43
 * @Description 处理权限数据-递归算法
 */
@Slf4j
public class PermissionTest {

    public static void main(String[] args) {
        String tempPath = "D:/permission.json";
        JSONObject jsonObject = JSONUtil.readJSONObject(new File(tempPath), Charset.defaultCharset());

        long start = System.currentTimeMillis();
        String json = JSONUtil.toJsonStr(jsonObject.get("data"));
        List<PermissionDTO> permissionList = JSONUtil.toList(json, PermissionDTO.class);
        if (CollUtil.isNotEmpty(permissionList)) {
            List<PermissionDTO> result = permissionList.stream().filter(p -> p.getParentId() == 0)
                    .peek((p) -> p.setChildren(getChildren(p, permissionList)))
                    .collect(Collectors.toList());
            log.info(JSONUtil.toJsonStr(result));
        }

        System.out.println(System.currentTimeMillis()-start);
    }

    private static List<PermissionDTO> getChildren(PermissionDTO dto, List<PermissionDTO> list) {
        return list.stream()
                .filter(p -> dto.getPermissionId().equals(p.getParentId()))
                .peek(p1 -> p1.setChildren(getChildren(p1, list))).collect(Collectors.toList());
    }
}
