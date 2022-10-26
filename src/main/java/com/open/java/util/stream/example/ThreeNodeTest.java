package com.open.java.util.stream.example;

import com.open.java.util.stream.cases.ThreeNode;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuxiaowei
 * @date 2022年10月24日 20:23
 * @Description
 */
public class ThreeNodeTest {

    /**
     * 创建树
     * @param nodeList
     * @param parentId
     * @return java.util.List<com.open.java.util.stream.cases.ThreeNode>
     */
    public static List<ThreeNode> getTree(List<ThreeNode> nodeList, Integer parentId) {
        return nodeList.stream()
                .filter(entity -> parentId.intValue() == entity.getParentId().intValue())
                .map(entity -> {
                    List<ThreeNode> childList = getTree(nodeList, entity.getId());
                    entity.setChildNode(childList);
                    entity.setChildNodeSize(childList.size());
                    return entity;
                }).collect(Collectors.toList());
    }

    /**
     * 递归查询子节点
     * @param root 根节点
     * @param all 所有节点
     * @return java.util.List<com.open.java.util.stream.cases.ThreeNode>
     */
    private static List<ThreeNode> getChildTree (ThreeNode root,List<ThreeNode> all){
        return all.stream()
                .filter(entity -> root.getId().intValue() == entity.getParentId().intValue())
                .map(entity -> {
                    List<ThreeNode> childList = getChildTree(root,all);
                    entity.setChildNode(childList);
                    entity.setChildNodeSize(childList.size());
                    return entity;
                }).collect(Collectors.toList());
    }

    /**
     * 遍历树形结构(有错误)
     * @param treeList
     * @return java.util.List<java.lang.Integer>
     */
    private static List<Integer> getTreeInfo (List<ThreeNode> treeList){
        return treeList.stream()
                .filter(entity -> entity.getChildNodeSize() != null && entity.getChildNodeSize() > 0)
                .map(entity -> getTreeInfo(entity.getChildNode()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * 判断节点是否是叶子节点
     * @param id
     * @param nodeList
     * @return boolean true是 false不是
     */
    public static boolean hasChildNode (Integer id,List<ThreeNode> nodeList){
        return nodeList.stream()
                .noneMatch(entity -> entity.getParentId().intValue() == id);
    }

    public static void main(String[] args) {
        List<ThreeNode> threeNodeList = new ArrayList<>() ;
        threeNodeList.add(new ThreeNode(1,"节点A",0)) ;
        threeNodeList.add(new ThreeNode(2,"节点B",1)) ;
        threeNodeList.add(new ThreeNode(3,"节点C",1)) ;
        threeNodeList.add(new ThreeNode(4,"节点D",1)) ;
        threeNodeList.add(new ThreeNode(5,"节点E",2)) ;
        threeNodeList.add(new ThreeNode(6,"节点F",2)) ;
        // 测试
        List<ThreeNode> getTree = getTree(threeNodeList,0) ;
        System.out.println(getTree);

        // 测试
        List<Integer> treeIdList = getTreeInfo(getTree) ;
        System.out.println(treeIdList);

        // 测试
//        System.out.println(hasChildNode(1,threeNodeList)) ;
//        System.out.println(hasChildNode(6,threeNodeList)) ;
    }

}
