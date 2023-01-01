package com.example.securitySchool.controller.teacherVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemInput {

    private Long paperTemplateId;

    private Long problemId;

    private String content;
    private String answer;

}
