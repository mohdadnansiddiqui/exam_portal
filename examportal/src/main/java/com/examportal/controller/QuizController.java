package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import com.examportal.dto.QuizDto;
import com.examportal.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	@Autowired
	private QuizService quizService;

	@PostMapping("/create")
	private ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto) {
		return new ResponseEntity<QuizDto>(this.quizService.createQuiz(quizDto), HttpStatus.CREATED);

	}

	@PutMapping("/update")
	private ResponseEntity<QuizDto> updateQuiz(@RequestBody QuizDto quizDto) {
		return new ResponseEntity<QuizDto>(this.quizService.updateQuiz(quizDto), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<QuizDto> getQuizById(@PathVariable Long id) {
		return new ResponseEntity<QuizDto>(this.quizService.getQuiz(id), HttpStatus.OK);

	}

	@GetMapping("/{index}/{size}")
	private ResponseEntity<?> getAllQuizzes(@PathVariable Integer index, @PathVariable Integer size) {
		PageRequest pr = PageRequest.of(index, size);
		return new ResponseEntity<>(this.quizService.getAllQuizzes(pr), HttpStatus.OK);

	}

	@GetMapping("/category/{id}")
	private ResponseEntity<?> getQuizByCategory(@PathVariable Long id) {
		return new ResponseEntity<>(this.quizService.getQuizByCategory(id), HttpStatus.OK);

	}

	@GetMapping("/category-active/{id}")
	private ResponseEntity<?> getQuizByCategoryAndActive(@PathVariable Long id) {
		return new ResponseEntity<>(this.quizService.getQuizByCategoryAndActive(id), HttpStatus.OK);

	}

	@GetMapping("/active")
	private ResponseEntity<?> getActiveQuizzes() {
		return new ResponseEntity<>(this.quizService.getQuizByActive(), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	private ResponseEntity<ApiResponse> deleteQuiz(@PathVariable Long id) {
		this.quizService.deleteQuiz(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("quizz deleted"), HttpStatus.OK);

	}

}
