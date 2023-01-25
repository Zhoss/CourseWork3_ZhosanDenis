package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    private ExaminerService out;
    private final Question question1 = new Question("Что означает ООП?", "Объектно-ориентированное программирование");

    @BeforeEach
    public void initOut() {
        out = new ExaminerServiceImpl(javaQuestionService);
    }

    @Test
    void getQuestions() {
        Set<Question> questionSet = new HashSet<>(Set.of(question1));

        when(javaQuestionService.getAll()).thenReturn(questionSet);
        when(javaQuestionService.getRandomQuestion()).thenReturn(question1);

        Assertions.assertEquals(questionSet, out.getQuestions(1));
    }

    @Test
    void shouldThrowResponseStatusExceptionWhenInputAmountMoreThenQuestionsNumber() {
        Set<Question> questionSet = new HashSet<>(Set.of(question1));

        when(javaQuestionService.getAll()).thenReturn(questionSet);

        assertThrows(ResponseStatusException.class,() -> out.getQuestions(2));
    }
}