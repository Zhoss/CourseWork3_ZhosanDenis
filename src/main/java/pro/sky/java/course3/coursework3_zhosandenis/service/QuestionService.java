package pro.sky.java.course3.coursework3_zhosandenis.service;

import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
