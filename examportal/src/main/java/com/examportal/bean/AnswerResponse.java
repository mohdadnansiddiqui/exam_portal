package com.examportal.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AnswerResponse {
	private Float marksGot;
	private Integer correctAnswer;
	private Integer attempted;

}
