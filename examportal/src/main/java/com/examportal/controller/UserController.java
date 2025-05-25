package com.examportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examportal.dto.UserDto;
import com.examportal.service.UserService;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	private UserService userService;
	//private BCryptPasswordEncoder bCryptPasswordEncoder;

	// @Autowired
	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userService = userService;
		//this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@PostMapping("/create-user")
	private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
	}

	@PutMapping("/update-user")
	private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {

		return new ResponseEntity<UserDto>(userService.updateUser(userDto), HttpStatus.OK);
	}

	@GetMapping("/{userName}")
	private ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {

		return new ResponseEntity<UserDto>(this.userService.getUserByUserName(userName), HttpStatus.OK);
	}

	@GetMapping("")
	private ResponseEntity<?> getUserByUserName() {

		return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
	}

}
