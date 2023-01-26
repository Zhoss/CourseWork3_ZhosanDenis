package pro.sky.java.course3.coursework3_zhosandenis.repository;

import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
