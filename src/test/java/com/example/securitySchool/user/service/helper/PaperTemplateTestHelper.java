package com.example.securitySchool.user.service.helper;


import com.example.securitySchool.paper.domain.PaperTemplate;
import com.example.securitySchool.paper.domain.Problem;
import com.example.securitySchool.paper.service.PaperTemplateService;
import com.example.securitySchool.user.domain.User;
import org.junit.jupiter.api.Assertions;

public class PaperTemplateTestHelper {

    private final PaperTemplateService paperTemplateService;

    public PaperTemplateTestHelper(PaperTemplateService paperTemplateService) {
        this.paperTemplateService = paperTemplateService;
    }

    public PaperTemplate createPaperTemplate(User teacher, String paperName) {
        PaperTemplate paperTemplate = PaperTemplate.builder()
                .name(paperName)
                .creator(teacher)
                .build();
        return paperTemplateService.save(paperTemplate);
    }

    public Problem addProblem(long paperTemplateId, Problem problem) {
        return paperTemplateService.addProblem(paperTemplateId, problem);
    }

    public static void assertPaperTemplate(PaperTemplate pt, User user, String paperName) {
        Assertions.assertNotNull(pt.getPaperTemplateId());
        Assertions.assertNotNull(pt.getCreated());
        Assertions.assertNotNull(pt.getUpdated());
        Assertions.assertEquals(paperName, pt.getName());
        Assertions.assertEquals(user.getUserId(), pt.getCreator().getUserId());
    }
}
