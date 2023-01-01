package com.example.securitySchool.user.service.helper;

import com.example.securitySchool.paper.domain.Problem;
import com.example.securitySchool.paper.repository.PaperTemplateRepository;
import com.example.securitySchool.paper.repository.ProblemRepository;
import com.example.securitySchool.paper.service.PaperTemplateService;
import com.example.securitySchool.paper.service.ProblemService;
import com.example.securitySchool.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class WithPaperTemplateTest extends WithUserTest{

    @Autowired
    protected PaperTemplateRepository paperTemplateRepository;

    @Autowired
    protected ProblemRepository problemRepository;

    protected PaperTemplateService paperTemplateService;
    protected PaperTemplateTestHelper paperTemplateTestHelper;
    protected ProblemService problemService;
    protected User teacher;

    protected void preparePaperTemplate(){
        this.problemRepository.deleteAll();
        this.paperTemplateRepository.deleteAll();
        prepareUserServices();

        this.problemService = new ProblemService(problemRepository);
        this.paperTemplateService = new PaperTemplateService(paperTemplateRepository, problemService);
        this.paperTemplateTestHelper = new PaperTemplateTestHelper(paperTemplateService);

        this.teacher = this.userTestHelper.createTeacher(school, "선생님1");
    }

    protected Problem problem(long ptId, String content, String answer) {
        return Problem.builder().paperTemplateId(ptId).content(content).answer(answer).build();
    }

}
