package com.examportal.dto;

import com.examportal.entity.exam.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class QuestionDtoUser {
	private Long id;
	private String content;
	private String image;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	@JsonIgnore
	private String answer;
	private String givenAnswer;
	private Quiz quiz;

}
