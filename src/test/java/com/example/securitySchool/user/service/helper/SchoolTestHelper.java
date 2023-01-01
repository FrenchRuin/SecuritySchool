package com.example.securitySchool.user.service.helper;


import com.example.securitySchool.user.domain.School;
import com.example.securitySchool.user.service.SchoolService;
import org.junit.jupiter.api.Assertions;


public class SchoolTestHelper {


    private final SchoolService schoolService;

    public SchoolTestHelper(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    public static School makeSchool(String name, String city) {
        return School.builder()
                .name(name)
                .city(city)
                .build();
    }

    public School createSchool(String name, String city){
        return schoolService.save(makeSchool(name, city));
    }

    public static void assertSchool(School school, String name, String city) {
        Assertions.assertNotNull(school.getSchoolId());
        Assertions.assertNotNull(school.getCreated());
        Assertions.assertNotNull(school.getUpdated());

        Assertions.assertEquals(name,school.getName());
        Assertions.assertEquals(city, school.getCity());
    }
}
