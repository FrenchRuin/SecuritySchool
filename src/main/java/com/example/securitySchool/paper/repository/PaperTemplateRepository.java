package com.example.securitySchool.paper.repository;

import com.example.securitySchool.paper.domain.PaperTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperTemplateRepository extends JpaRepository<PaperTemplate, Long> {

    List<PaperTemplate> findAllByUserIdOrderByCreatedDesc(Long userId);

    Page<PaperTemplate> findAllByUserIdOrderByCreatedDesc(Long userId, Pageable pageable);

    long countByUserId(Long userId);

}