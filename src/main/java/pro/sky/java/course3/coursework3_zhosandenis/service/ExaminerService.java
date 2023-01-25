package pro.sky.java.course3.coursework3_zhosandenis.service;

import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
