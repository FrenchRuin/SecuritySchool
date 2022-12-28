package com.example.paper.repository;

import com.example.paper.domain.PaperAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperAnswerRepository extends JpaRepository<PaperAnswer, PaperAnswer.PaperAnswerId> {
}
