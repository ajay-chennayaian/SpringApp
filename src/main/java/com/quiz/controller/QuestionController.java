package com.quiz.controller;

import com.quiz.model.Question;
import com.quiz.dao.QuestionDao;
import com.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    private QuestionDao questionDao;

    @GetMapping("/allquestion")
    public ResponseEntity<List<Question>> getAllQuestions() {
       return questionService.getAllQUestions();
    }
    @GetMapping("/category/{category}")
    public List<Question> getAllQuestionsByCategory(@PathVariable String category) {
        return questionDao.findByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Question question) {
        return  questionService.addQuestion(question);
    }
}
