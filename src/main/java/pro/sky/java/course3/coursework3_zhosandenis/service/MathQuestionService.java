package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;
import pro.sky.java.course3.coursework3_zhosandenis.repository.QuestionRepository;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository repository;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        return this.repository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return this.repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return this.repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Object[] questionsArr = this.repository.getAll().toArray();
        Random random = new Random();
        int rand = random.nextInt(this.repository.getAll().size());
        return (Question) questionsArr[rand];
    }
}
