package com.examportal.bean;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ValidationResponse {
	private Date currentTimestamp;
	private Integer status;
	private List<String> errors;
}
