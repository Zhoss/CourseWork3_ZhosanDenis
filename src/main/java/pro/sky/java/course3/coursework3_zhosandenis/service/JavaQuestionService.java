package pro.sky.java.course3.coursework3_zhosandenis.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course3.coursework3_zhosandenis.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        if (checkInput(question, answer)) {
            Question newQuestion = new Question(question, answer);
            this.questions.add(newQuestion);
            return newQuestion;
        } else {
            throw new IllegalArgumentException("Требуется ввести корректные данные (вопрос - ответ)");
        }
    }

    @Override
    public Question add(Question question) {
        if (checkInput(question)) {
            this.questions.add(question);
            return question;
        } else {
            throw new IllegalArgumentException("Требуется ввести корректные данные");
        }
    }

    @Override
    public Question remove(Question question) {
        if (!checkInput(question)) {
            throw new IllegalArgumentException("Требуется ввести корректные данные");
        } else if (this.questions.contains(question)) {
            this.questions.remove(question);
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return this.questions;
    }

    @Override
    public Question getRandomQuestion() {
        Object[] questionsArr = this.questions.toArray();
        Random random = new Random();
        int rand = random.nextInt(this.questions.size());
        return (Question) questionsArr[rand];
    }

    private boolean checkInput(String question, String answer) {
        return question != null && !question.isEmpty() && !question.isBlank() &&
                answer != null && !answer.isEmpty() && !answer.isBlank();
    }

    private boolean checkInput(Question question) {
        return question != null && checkInput(question.getQuestion(), question.getAnswer());
    }
}
