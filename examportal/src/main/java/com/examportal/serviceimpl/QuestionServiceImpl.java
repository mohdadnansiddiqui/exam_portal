package com.examportal.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.bean.AnswerResponse;
import com.examportal.dto.QuestionDtoAdmin;
import com.examportal.dto.QuestionDtoUser;
import com.examportal.dto.QuizDto;
import com.examportal.entity.exam.Question;
import com.examportal.repository.QuestionRepository;
import com.examportal.service.QuestionService;
import com.examportal.service.QuizService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizService quizService;

	@Override
	public QuestionDtoAdmin createQuestion(QuestionDtoAdmin dtoAdmin) {
		Question question = new Question();
		BeanUtils.copyProperties(dtoAdmin, question);
		Question save = questionRepository.save(question);
		BeanUtils.copyProperties(save, dtoAdmin);

		return dtoAdmin;
	}

	@Override
	public QuestionDtoAdmin updateQuestion(QuestionDtoAdmin dtoAdmin) {
		Question question = new Question();
		BeanUtils.copyProperties(dtoAdmin, question);
		Question save = questionRepository.save(question);
		BeanUtils.copyProperties(save, dtoAdmin);

		return dtoAdmin;

	}

	@Override
	public Set<QuestionDtoAdmin> getAllQuestion() {
		Set<QuestionDtoAdmin> question = new HashSet<>();
		copySet(questionRepository.findAll().parallelStream().collect(Collectors.toSet()), question);
		return question;
	}

	@Override
	public List<QuestionDtoUser> getAllQuizOfQuestion(Long id) {
		List<QuestionDtoUser> question = new ArrayList<>();
		QuizDto quiz = quizService.getQuiz(id);
		Set<Question> questions = quiz.getQuestions();
		List<Question> collect = questions.stream().collect(Collectors.toList());
		if (questions.size() > quiz.getNumOfQuestions()) {
			Collections.shuffle(collect);
			collect = collect.subList(0, quiz.getNumOfQuestions() + 1);
		}
		List<Question> collect2 = collect.stream().map(c -> {
			c.setAnswer("");
			return c;
		}).collect(Collectors.toList());
		Collections.shuffle(collect2);
		collect2.forEach(q -> {
			QuestionDtoUser dtoUser = new QuestionDtoUser();
			BeanUtils.copyProperties(q, dtoUser);
			question.add(dtoUser);
		});
		return question;
	}

	@Override
	public QuestionDtoAdmin getQuestion(Long id) {
		QuestionDtoAdmin dtoAdmin= new QuestionDtoAdmin();
		BeanUtils.copyProperties(questionRepository.findById(id).get(), dtoAdmin);
		return dtoAdmin;
	}

	@Override
	public void deleteQuestion(Long id) {
		questionRepository.deleteById(id);

	}

	@Override
	public List<QuestionDtoAdmin> getQuizOfAllQuestion(Long id) {
		List<QuestionDtoAdmin> dto = new ArrayList<>();
		QuizDto quiz = quizService.getQuiz(id);
		Set<Question> questions = quiz.getQuestions();
		List<Question> collect = questions.stream().collect(Collectors.toList());
		collect.forEach(q -> {
			QuestionDtoAdmin dtoUser = new QuestionDtoAdmin();
			BeanUtils.copyProperties(q, dtoUser);
			dto.add(dtoUser);
		});

		return dto;
	}

	@Override
	public AnswerResponse resultQuiz(List<Question> question) {
		Float marksGot = 0.0F;
		Integer correctAnswer = 0;
		Integer attempted = 0;
		for (Question q : question) {
			Question que = this.questionRepository.findById(q.getId()).get();
			if (q.getGivenAnswer().trim().equals(que.getAnswer())) {
				correctAnswer++;
				float perQuestionMarks = Float.parseFloat(q.getQuiz().getMaxMarks()) / question.size();
				marksGot += perQuestionMarks;
			}
			if (q.getGivenAnswer().trim() != "") {
				attempted++;
			}

		}

		return new AnswerResponse(marksGot, correctAnswer, attempted);
	}

	Set<QuestionDtoAdmin> copySet(Set<Question> quiz, Set<QuestionDtoAdmin> quizzes) {
		quiz.forEach(q -> {
			QuestionDtoAdmin dto = new QuestionDtoAdmin();
			BeanUtils.copyProperties(q, dto);
			quizzes.add(dto);
		});

		return quizzes;

	}

}
