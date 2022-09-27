package com.open.mapstruct;

import com.open.HelloUtilsApplication;
import com.open.mapstruct.cases.SchoolPO;
import com.open.mapstruct.cases.SchoolStudentVO;
import com.open.mapstruct.cases.StudentPO;
import com.open.mapstruct.cases.StudentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaowei
 * @date 2022年09月26日 17:54
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloUtilsApplication.class)
public class MapstructTest {

    @Test
    public void test() {
        StudentPO build = StudentPO.builder()
                .id(10)
                .name("test")
                .age(24)
                .className("教室1")
                .build();
        StudentVO studentVO = MapstructDemo.INSTANCE.po2Vo(build);
        System.out.println(studentVO);

        List<StudentPO> studentPOList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            StudentPO studentPO = StudentPO.builder().id(i).name(String.valueOf(i)).age(i).build();
            studentPOList.add(studentPO);
        }
        List<StudentVO> studentVOs = MapstructDemo.INSTANCE.poList2VoList(studentPOList);
        System.out.println(studentVOs);

        SchoolPO schoolPO = SchoolPO.builder().name("学校名字").build();
        StudentPO studentPO = StudentPO.builder().name("学生名字").build();
        SchoolStudentVO schoolStudentVO = MapstructDemo.INSTANCE.mergeVo(schoolPO, studentPO);
        System.out.println(schoolStudentVO);
    }
}