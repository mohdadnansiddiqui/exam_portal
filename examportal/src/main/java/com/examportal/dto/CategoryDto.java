package com.examportal.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.examportal.entity.exam.Quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Long id;
	@NotBlank(message = "Please enter proper title")
	@Size(min = 5, message = "title should be atleast 5 characters")
	private String title;
	@NotBlank(message = "Please enter proper description")
	@Size(min = 50, message = "description should be atleast 5 characters")
	private String description;
	private Set<Quiz> quizzes = new LinkedHashSet<>();

}
