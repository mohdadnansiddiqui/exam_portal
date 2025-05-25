package com.examportal.service;

import java.util.List;
import java.util.Set;

import com.examportal.bean.AnswerResponse;
import com.examportal.dto.QuestionDtoAdmin;
import com.examportal.dto.QuestionDtoUser;
import com.examportal.entity.exam.Question;

public interface QuestionService {
	QuestionDtoAdmin createQuestion(QuestionDtoAdmin question);

	QuestionDtoAdmin updateQuestion(QuestionDtoAdmin question);

	Set<QuestionDtoAdmin> getAllQuestion();

	List<QuestionDtoUser> getAllQuizOfQuestion(Long id);

	List<QuestionDtoAdmin> getQuizOfAllQuestion(Long id);

	QuestionDtoAdmin getQuestion(Long id);

	void deleteQuestion(Long id);

	AnswerResponse resultQuiz(List<Question> question);

}
