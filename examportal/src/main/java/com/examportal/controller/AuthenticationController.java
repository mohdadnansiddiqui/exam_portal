package com.examportal.controller;

import java.security.Principal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.bean.JwtRequest;
import com.examportal.bean.JwtResponse;
import com.examportal.bean.JwtUtil;
import com.examportal.config.UserDetailsCustom;
import com.examportal.dto.UserDto;
import com.examportal.entity.User;
import com.examportal.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	UserDetailsCustom userDetailsCustom;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/generate-token")
	private ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		authentication(jwtRequest.getUserName(), jwtRequest.getPassword());

		String generateToken = jwtUtil.generateToken(userDetailsCustom.loadUserByUsername(jwtRequest.getUserName()));
		return new ResponseEntity<>(new JwtResponse(generateToken), HttpStatus.CREATED);
	}

	void authentication(String userName, String passoword) throws Exception {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, passoword));

	}

	@GetMapping("/current-user")
	private ResponseEntity<UserDto> getCurrentUser(Principal principal) {
		UserDto userDto = new UserDto();
		User user = userRepository.findByUserName(principal.getName());
		BeanUtils.copyProperties(user, userDto);
		user.getUserRole().forEach(u->
		userDto.setRole(u.getRole().getRole())
		);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

}
