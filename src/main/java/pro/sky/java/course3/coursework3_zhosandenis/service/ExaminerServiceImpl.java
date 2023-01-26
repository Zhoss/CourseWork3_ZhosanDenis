package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServiceList;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService) {
        this.questionServiceList = new ArrayList<>(List.of(javaQuestionService, mathQuestionService));
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > 0 && amount <= (questionServiceList.get(0).getAll().size() + questionServiceList.get(1).getAll().size())) {
            Random random = new Random();
            Set<Question> questionSet = new HashSet<>();
            for (int i = 0; i < amount + 1; i++) {
                int javaRand = random.nextInt(questionServiceList.get(0).getAll().size());
                int mathRand = random.nextInt(questionServiceList.get(1).getAll().size());
                if (javaRand >= mathRand) {
                    questionSet.add(questionServiceList.get(0).getRandomQuestion());
                } else {
                    questionSet.add(questionServiceList.get(1).getRandomQuestion());
                }
            }
            return questionSet;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
