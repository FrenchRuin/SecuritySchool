package com.example.securitySchool.user.service;


import com.example.securitySchool.user.domain.School;
import com.example.securitySchool.user.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public School save(School school) {
        if (school.getSchoolId() == null) {
            school.setCreated(LocalDateTime.now());
        }
        school.setUpdated(LocalDateTime.now());
        return schoolRepository.save(school);
    }

    public Optional<School> findSchool(Long schoolId) {
        return schoolRepository.findById(schoolId);
    }

    public Optional<School> updateName(Long schoolId, String name) {
        return schoolRepository.findById(schoolId).map(school -> {
            school.setName(name);
            schoolRepository.save(school);
            return school;
        });
    }

    public List<School> getSchoolList(String city) {
        return schoolRepository.findAllByCity(city);
    }

    public Page<School> list(int pageNum, int size) {
        return schoolRepository.findAllByOrderByCreatedDesc(PageRequest.of(pageNum - 1, size));
    }
    public List<String> cities() {
        return schoolRepository.getCities();
    }

    public long count() {
        return schoolRepository.count();
    }

    public List<School> findAllByCity(String city) {
        return schoolRepository.findAllByCity(city);
    }
}
