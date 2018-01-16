package org.forten.scss.bo;

import org.forten.BaseTest;
import org.forten.dto.Message;
import org.forten.scss.dto.qo.CourseQoForTeacher;
import org.forten.scss.dto.vo.CourseForTeacher;
import org.forten.scss.entity.Course;
import org.forten.utils.common.DateUtil;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class CourseBoTest extends BaseTest{
    @Resource
    private CourseBo bo;

    @Test
    public void testDoSave(){
        Course course = new Course();
        course.setName("论共产主义实现");
        course.setBeginSelectTime(DateUtil.getCommonTime(2017, 2, 1, 8, 0, 0));
        course.setEndSelectTime(DateUtil.getCommonTime(2017, 2, 10, 17, 0, 0));
        course.setbeginTeachTime(DateUtil.getCommonTime(2017, 2, 15, 10, 0, 0));
        course.setendTeachTime(DateUtil.getCommonTime(2017, 2, 15, 12, 0, 0));
        course.setClassroom("主楼214");
        course.setCredit(5);
        course.setTeacher("钱小黄");
        course.setIntro("论共产主义实现吗？");
        course.setServiceTeacher("刘丰");
        course.setServiceTeacherTel("1332828889");
        course.setMaxAmount(55);

        Message m = bo.doSave(course);

        assertNotNull(m);
        assertEquals("info", m.getTypeDes());
    }

    @Test
    public void testQueryForTeacher(){
        Date begin = DateUtil.getCommonTime(2017,3,1);
        Date end = DateUtil.getCommonTime(2017,5,1);
        CourseQoForTeacher qo = new CourseQoForTeacher();


        List<CourseForTeacher> list = bo.queryBy(qo);
        list.forEach(System.out::println);
    }
}