package com.examportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.bean.ApiResponse;
import com.examportal.dto.QuestionDtoAdmin;
import com.examportal.entity.exam.Question;
import com.examportal.service.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@PostMapping("/create")
	private ResponseEntity<QuestionDtoAdmin> createQuestion(@RequestBody QuestionDtoAdmin questionAdmin) {
		return new ResponseEntity<QuestionDtoAdmin>(questionService.createQuestion(questionAdmin), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	private ResponseEntity<QuestionDtoAdmin> updateQuestion(@RequestBody QuestionDtoAdmin question) {
		return new ResponseEntity<QuestionDtoAdmin>(questionService.updateQuestion(question), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<QuestionDtoAdmin> getQuestionById(@PathVariable Long id) {
		return new ResponseEntity<QuestionDtoAdmin>(questionService.getQuestion(id), HttpStatus.OK);
	}

	@GetMapping("")
	private ResponseEntity<?> getQuestions() {
		return new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
	}

	@GetMapping("quiz/{id}")
	private ResponseEntity<?> getQuestionByQuiz(@PathVariable Long id) {
		return new ResponseEntity<>(questionService.getQuizOfAllQuestion(id), HttpStatus.OK);
	}

	@GetMapping("quizzes/{id}")
	private ResponseEntity<?> getAllQuestionByQuiz(@PathVariable Long id) {
		return new ResponseEntity<>(questionService.getAllQuizOfQuestion(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<ApiResponse> deteletQuiz(@PathVariable Long id) {
		questionService.deleteQuestion(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("quizz deleted"), HttpStatus.OK);
	}

	@PostMapping("/result-quiz")
	private ResponseEntity<?> resultQuiz(@RequestBody List<Question> question) {
		return new ResponseEntity<>(questionService.resultQuiz(question), HttpStatus.CREATED);
	}

}
