package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > 0 && amount <= (javaQuestionService.getAll().size() + mathQuestionService.getAll().size())) {
            Random random = new Random();
            Set<Question> questionSet = new HashSet<>();
            for (int i = 0; i < amount + 1; i++) {
                int javaRand = random.nextInt(this.javaQuestionService.getAll().size());
                int mathRand = random.nextInt(this.mathQuestionService.getAll().size());
                if (javaRand >= mathRand) {
                    questionSet.add(javaQuestionService.getRandomQuestion());
                } else {
                    questionSet.add(mathQuestionService.getRandomQuestion());
                }
            }
            return questionSet;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
