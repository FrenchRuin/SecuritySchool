package com.example.user.service.helper;

import com.example.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class StudentTest extends WithUserTest{

    User teacher;
    User student;

    @BeforeEach
    protected void before() {
        prepareUserServices();
        this.teacher = this.userTestHelper.createTeacher(school, "teacher1");
        this.student = this.userTestHelper.createStudent(school, teacher, "student1", "1");
    }

    @DisplayName("1. 학습자 등록을 한다. ")
    @Test
    void test_1() {
        List<User> studentList = userService.findStudentList();
//        Assertions.assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "student1", "1");
    }

    @DisplayName("2. 선생님으로 등록하면 선생님의 학습지가 조회된다.")
    @Test
    void test_2() {
        List<User> studentList = userService.findTeacherStudentList(teacher.getUserId());
        Assertions.assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "student1", "1");
    }

    @DisplayName("3. 학교로 학습지가 조회된다.")
    @Test
    void test_3() {
        List<User> studentList = userService.findBySchoolStudentList(school.getSchoolId());
        Assertions.assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school,teacher,studentList.get(0),"student1","1");
    }



}
