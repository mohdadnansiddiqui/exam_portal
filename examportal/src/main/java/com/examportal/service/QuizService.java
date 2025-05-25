package com.examportal.service;

import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.examportal.dto.QuizDto;

public interface QuizService {
	QuizDto createQuiz(QuizDto quizDto);

	QuizDto updateQuiz(QuizDto quizDto);

	Set<QuizDto> getAllQuizzes(Pageable pageable);

	QuizDto getQuiz(Long id);	
	Set<QuizDto> getQuizByCategory(Long id);
	Set<QuizDto> getQuizByCategoryAndActive(Long id);
	Set<QuizDto> getQuizByActive();

	void deleteQuiz(Long id);
}
