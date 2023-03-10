package com.example.securitySchool.user.repository;

import com.example.securitySchool.user.domain.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("select distinct (city) from School")
    List<String> getCities();

    List<School> findAllByCity(String city);

    Page<School> findAllByOrderByCreatedDesc(PageRequest of);
}
