package com.examportal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.entity.exam.Question;
import com.examportal.entity.exam.Quiz;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	 Set<Question> findByQuiz(Quiz quiz);
	

}
