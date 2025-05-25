package com.examportal.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.entity.exam.Category;
import com.examportal.entity.exam.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	Set<Quiz> findByCategory(Category category);

	Set<Quiz> findByCategoryAndActive(Category category, Boolean active);

	Set<Quiz> findByActive(Boolean active);

	Page<Quiz> findAll(Pageable pageable);

}
