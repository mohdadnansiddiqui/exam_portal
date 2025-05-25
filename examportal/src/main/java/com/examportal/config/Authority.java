package com.examportal.config;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	String authroties;
	

	public Authority(String authroties) {
		super();
		this.authroties = authroties;
	}


	@Override
	public String getAuthority() {
		
		return authroties;
	}

}
