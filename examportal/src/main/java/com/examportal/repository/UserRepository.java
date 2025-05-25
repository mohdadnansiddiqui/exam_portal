package com.examportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
		
}
