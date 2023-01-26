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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionServiceMock;
    @Mock
    private MathQuestionService mathQuestionServiceMock;
    private ExaminerService out;
    private final Question question1 = new Question("Что означает ООП?", "Объектно-ориентированное программирование");
    private final Question question2 = new Question("Каким словом обозначался миллион в Древней Руси?", "Тьма");

    @BeforeEach
    public void initOut() {
        out = new ExaminerServiceImpl(javaQuestionServiceMock, mathQuestionServiceMock);
    }

    @Test
    void getQuestions() {
        Set<Question> questionJavaSet = new HashSet<>(Set.of(question1, question2));
        Set<Question> questionMathSet = new HashSet<>(Set.of(question2));
        Set<Question> questionsSet = new HashSet<>(Set.of(question1));

        when(javaQuestionServiceMock.getAll()).thenReturn(questionJavaSet);
        when(mathQuestionServiceMock.getAll()).thenReturn(questionMathSet);

        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(question1);

        Assertions.assertEquals(questionsSet, out.getQuestions(2));

        verify(javaQuestionServiceMock, times(3)).getRandomQuestion();
    }

    @Test
    void shouldThrowResponseStatusExceptionWhenInputAmountMoreThenQuestionsNumber() {
        Set<Question> questionJavaSet = new HashSet<>(Set.of(question1));
        Set<Question> questionMathSet = new HashSet<>(Set.of(question2));

        when(javaQuestionServiceMock.getAll()).thenReturn(questionJavaSet);
        when(mathQuestionServiceMock.getAll()).thenReturn(questionMathSet);

        assertThrows(ResponseStatusException.class,() -> out.getQuestions(3));
    }
}