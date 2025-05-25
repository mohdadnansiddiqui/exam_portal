package com.examportal.serviceimpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examportal.dto.QuizDto;
import com.examportal.entity.exam.Category;
import com.examportal.entity.exam.Quiz;
import com.examportal.repository.CategoryRepository;
import com.examportal.repository.QuizRepository;
import com.examportal.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepository quizRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public QuizDto createQuiz(QuizDto quizDto) {
		Quiz quiz = new Quiz();
		BeanUtils.copyProperties(quizDto, quiz);
		Quiz save = quizRepository.save(quiz);
		BeanUtils.copyProperties(save, quizDto);
		return quizDto;
	}

	@Override
	public QuizDto updateQuiz(QuizDto quizDto) {
		Quiz quiz = new Quiz();
		BeanUtils.copyProperties(quizDto, quiz);
		Quiz save = quizRepository.save(quiz);
		BeanUtils.copyProperties(save, quizDto);
		return quizDto;

	}

	@Override
	public Set<QuizDto> getAllQuizzes(Pageable pageable) {
		Long count = this.quizRepository.count();
		Set<QuizDto> quizzes = new HashSet<>();
		copySet(quizRepository.findAll((pageable)).stream().collect(Collectors.toSet()), quizzes);
		quizzes.forEach(qu->{
			qu.setSize(count);
		});
		return quizzes;
	}

	@Override
	public QuizDto getQuiz(Long id) {
		QuizDto dto = new QuizDto();
		BeanUtils.copyProperties(quizRepository.findById(id).get(), dto);
		return dto;
	}

	@Override
	public void deleteQuiz(Long id) {
		quizRepository.deleteById(id);

	}

	@Override
	public Set<QuizDto> getQuizByCategory(Long id) {
		Set<QuizDto> quizzes = new HashSet<>();
		Category category = categoryRepository.findById(id).get();
		copySet(this.quizRepository.findByCategory(category), quizzes);

		return quizzes;
	}

	@Override
	public Set<QuizDto> getQuizByCategoryAndActive(Long id) {
		Set<QuizDto> quizzes = new HashSet<>();
		Category category = categoryRepository.findById(id).get();
		copySet(this.quizRepository.findByCategoryAndActive(category, true), quizzes);

		return quizzes;
	}

	@Override
	public Set<QuizDto> getQuizByActive() {

		Set<QuizDto> quizzes = new HashSet<>();
		copySet(this.quizRepository.findByActive(true), quizzes);
		return quizzes;
	}

	Set<QuizDto> copySet(Set<Quiz> quiz, Set<QuizDto> quizzes) {
		quiz.forEach(q -> {
			QuizDto dto = new QuizDto();
			BeanUtils.copyProperties(q, dto);
			quizzes.add(dto);
		});

		return quizzes;

	}
}
