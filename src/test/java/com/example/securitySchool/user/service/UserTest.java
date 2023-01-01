package com.example.securitySchool.user.service;

import com.example.securitySchool.user.domain.Authority;
import com.example.securitySchool.user.domain.User;
import com.example.securitySchool.user.service.helper.UserTestHelper;
import com.example.securitySchool.user.service.helper.WithUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@DataJpaTest
public class UserTest extends WithUserTest {

    @BeforeEach
    protected void before() {
        prepareUserServices();
    }

    @DisplayName("1. 사용자 생성")
    @Test
    void test_1(){
        userTestHelper.createUser(school, "user1");
        List<User> list = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Assertions.assertEquals(1, list.size());
        UserTestHelper.assertUser(school, list.get(0), "user1");
    }

    @DisplayName("2. 이름 수정")
    @Test
    void test_2(){
        User user = userTestHelper.createUser(school, "user1");
        userService.updateUsername(user.getUserId(), "user2");
        List<User> list = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Assertions.assertEquals("user2", list.get(0).getName());
    }

    @DisplayName("3. 권한 부여")
    @Test
    void test_3(){
        User user = userTestHelper.createUser(school, "user1", Authority.ROLE_STUDENT);
        userService.addAuthority(user.getUserId(), Authority.ROLE_TEACHER);
        User savedUser = userService.findUser(user.getUserId()).get();
        userTestHelper.assertUser(school, savedUser, "user1", Authority.ROLE_STUDENT, Authority.ROLE_TEACHER);
    }

    @DisplayName("4. 권한 취소 ")
    @Test
    void test_4(){
        User user1 = userTestHelper.createUser(school, "admin", Authority.ROLE_STUDENT, Authority.ROLE_TEACHER);
        userService.removeAuthority(user1.getUserId(), Authority.ROLE_STUDENT);
        User savedUser = userService.findUser(user1.getUserId()).get();
        userTestHelper.assertUser(school, savedUser, "admin", Authority.ROLE_TEACHER);
    }


    @DisplayName("5. 이메일 검색 ")
    @Test
    void test_5(){
        User user1 = userTestHelper.createUser(school, "user1");
        User saved = (User) userSecurityService.loadUserByUsername("user1@test.com");
        userTestHelper.assertUser(school, saved, "user1");
    }

    @DisplayName("6. ROLE 중복해서 추가되지 않는다.")
    @Test
    void Role_중복_문제() {
        User user1 = userTestHelper.createUser(school, "user1", Authority.ROLE_STUDENT);
        userService.addAuthority(user1.getUserId(),Authority.ROLE_STUDENT);
        userService.addAuthority(user1.getUserId(),Authority.ROLE_STUDENT);
        User saved = userService.findUser(user1.getUserId()).get();
        Assertions.assertEquals(1, saved.getAuthorities().size());
        userTestHelper.assertUser(school, saved, "user1", Authority.ROLE_STUDENT);
    }

    @DisplayName("7. 이메일이 중복되어서 들어가는가? ")
    @Test
    void 이메일_중복문제(){
        userTestHelper.createUser(school, "user1");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userTestHelper.createUser(school, "user1");
        });

    }
}
