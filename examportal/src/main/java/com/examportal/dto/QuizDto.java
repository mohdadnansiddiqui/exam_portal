package com.examportal.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.examportal.entity.exam.Category;
import com.examportal.entity.exam.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class QuizDto {
	private Long id;
	@NotBlank(message = "please enter title!! ")
	private String title;
	@NotBlank(message = "please enter description!! ")
	private String description;
	@NotBlank(message = "please enter maxMarks!! ")
	private String maxMarks;
	@NotBlank(message = "please enter numOfQuestions!! ")
	private Integer numOfQuestions;
	private Boolean active;
	private Category category;
	private Long size;
	private Set<Question> questions = new LinkedHashSet<>();

}
