package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@SessionScope
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= questionService.getAll().size()) {
            Set<Question> questionSet = new HashSet<>();
            for (int i = 0; i < amount + 1; i++) {
                questionSet.add(questionService.getRandomQuestion());
            }
            return questionSet;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
