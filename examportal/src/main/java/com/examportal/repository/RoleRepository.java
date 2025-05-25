package com.examportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
