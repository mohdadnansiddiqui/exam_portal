package com.examportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.examportal.entity.User;
import com.examportal.repository.UserRepository;

@Component
public class UserDetailsCustom implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Does Not Exist");
		}
		CustomUserDetail customUserDetail = new CustomUserDetail(user);
		System.out.println("pas--->"+customUserDetail.getPassword());

		return customUserDetail;
	}

}
