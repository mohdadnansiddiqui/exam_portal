package com.examportal.service;

import java.util.List;

import com.examportal.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto getUserByUserName(String userName);
	UserDto updateUser(UserDto userDto);
	List<UserDto> getUsers();

}
