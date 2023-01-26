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
public class MathQuestionService implements QuestionService {

    @Override
    public Question add(String question, String answer) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Question add(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Question remove(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Collection<Question> getAll() {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Question getRandomQuestion() {
        Question question1 = new Question("Каким словом обозначался миллион в Древней Руси?", "Тьма");
        Question question2 = new Question("Как называется сотая часть числа?", "Процент");
        Question question3 = new Question("Назовите единицу массы драгоценных камней", "Карат");
        Question question4 = new Question("Другое название независимой переменной", "Аргумент");
        Question question5 = new Question("Как называется функция, графиком которой является парабола?", "Квадратичная");
        Set<Question> questionSet = new HashSet<>();

        questionSet.add(question1);
        questionSet.add(question2);
        questionSet.add(question3);
        questionSet.add(question4);
        questionSet.add(question5);

        Object[] questionsArr = questionSet.toArray();
        Random random = new Random();
        int rand = random.nextInt(questionSet.size());
        return (Question) questionsArr[rand];
    }
}
