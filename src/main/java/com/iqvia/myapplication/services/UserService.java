package com.iqvia.myapplication.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.iqvia.myapplication.dtos.CreateUserRequest;
import com.iqvia.myapplication.dtos.UserViewResponse;
import com.iqvia.myapplication.security.configuration.CurrentUser;

public interface UserService {
	
	public void registerUser(CreateUserRequest userRequest);

	public String test();

	public UserViewResponse viewUser();
	
//	public void enableDisableUser(UserRequest userRequest);

}
