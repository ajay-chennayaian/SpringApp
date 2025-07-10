package com.quiz.service;

import com.quiz.QuestionWrapper;
import com.quiz.Response;
import com.quiz.dao.QuestionDao;
import com.quiz.dao.QuizDao;
import com.quiz.model.Question;
import com.quiz.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    public String createQuiz(String category, String qTitle, int noQs) {
        List<Question> questions = questionDao.findRandomByCategory(category, PageRequest.of(2,noQs));

        Quiz quiz = new Quiz();
        quiz.setQuestions(questions);
        quiz.setTitle(qTitle);

        quizDao.save(quiz);

        return "success";

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestionTitle(question.getQuestionTitle());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submit(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response : responses) {
            if(response.getAnswer().equals( questions.get(i).getRightAnswer())) {
               right++;
            }
            i++;
        }
        return ResponseEntity.ok().body(right);
    }
}
