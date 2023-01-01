package com.example.securitySchool.paper.repository;

import com.example.securitySchool.paper.domain.PaperAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperAnswerRepository extends JpaRepository<PaperAnswer, PaperAnswer.PaperAnswerId> {
}
