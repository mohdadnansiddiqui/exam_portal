package com.examportal.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examportal.dto.UserDto;
import com.examportal.entity.Role;
import com.examportal.entity.User;
import com.examportal.entity.UserRole;
import com.examportal.repository.RoleRepository;
import com.examportal.repository.UserRepository;
import com.examportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public UserServiceImpl() {
	}

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = new User();
		Role role = null;
		BeanUtils.copyProperties(userDto, user);

		if (userDto.getRole().equalsIgnoreCase("admin"))
			role = new Role(1, "admin", null);
		else
			role = new Role(2, "normal", null);

		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		Set<UserRole> set = new HashSet<>();
		set.add(userRole);
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		set.forEach(u -> this.roleRepository.save(u.getRole()));
		user.getUserRole().addAll(set);
		BeanUtils.copyProperties(this.userRepository.save(user), userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserName(String userName) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(this.userRepository.findByUserName(userName), userDto);

		return userDto;
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> list = this.userRepository.findAll();
		List<UserDto> userDto = new ArrayList<>();
		list.forEach(l -> {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(l, dto);
			userDto.add(dto);
		});

		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user2 = userRepository.findById(userDto.getId()).get();
		User user = new User();

		BeanUtils.copyProperties(userDto, user);
		user.setUserRole(user2.getUserRole());

		BeanUtils.copyProperties(this.userRepository.save(user), userDto);

		return userDto;
	}

}
