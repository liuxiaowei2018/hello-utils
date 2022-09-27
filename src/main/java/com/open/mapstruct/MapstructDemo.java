package com.open.mapstruct;

import com.open.mapstruct.cases.SchoolPO;
import com.open.mapstruct.cases.SchoolStudentVO;
import com.open.mapstruct.cases.StudentPO;
import com.open.mapstruct.cases.StudentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年09月26日 17:49
 * @Description
 */
@Mapper
public interface MapstructDemo {

    MapstructDemo INSTANCE = Mappers.getMapper(MapstructDemo.class);

    /**
     * po->vo
     * @param studentPO
     * @return
     */
    @Mappings({
            @Mapping(source = "name", target = "studentName"),
            @Mapping(source = "age", target = "studentAge")
    })
    StudentVO po2Vo(StudentPO studentPO);

    /**
     * List<StudentPO>->List<StudentVO>
     * List类型互转的映射规则会引用单个对象的映射规则
     * @param studentPO
     * @return
     */
    List<StudentVO> poList2VoList(List<StudentPO> studentPO);

    /**
     * merge(o1,o2)
     * @param schoolPO
     * @param studentPO
     * @return com.open.mapstruct.cases.SchoolStudentVO
     */
    @Mappings({
            @Mapping(source = "schoolPO.name", target = "schoolName"),
            @Mapping(source = "studentPO.name", target = "studentName")
    })
    SchoolStudentVO mergeVo(SchoolPO schoolPO, StudentPO studentPO);
}
