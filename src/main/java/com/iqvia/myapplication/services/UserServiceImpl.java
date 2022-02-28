package com.iqvia.myapplication.services;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iqvia.myapplication.dtos.CreateUserRequest;
import com.iqvia.myapplication.dtos.UserViewResponse;
import com.iqvia.myapplication.entites.User;
import com.iqvia.myapplication.exceptions.ResourceNotFoundException;
import com.iqvia.myapplication.exceptions.UserAlreadyPresentException;
import com.iqvia.myapplication.repositories.UserRepository;
import com.iqvia.myapplication.security.configuration.CurrentUser;
	
@Service
public class UserServiceImpl implements UserService{
	
//	@Autowired
	private UserRepository  userRepository;
	
//	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository  userRepository,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public void registerUser(CreateUserRequest userRequest) {
		
		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setMailId(userRequest.getMailId());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		if(userRepository.existsById(userRequest.getUserName()))
		{
			throw new UserAlreadyPresentException("User " + userRequest.getUserName() + " already registered");
		}
		else {
			userRepository.save(user);
		}
	}
	
	@Override
	public UserViewResponse viewUser(){
		 Principal principal = SecurityContextHolder.getContext().getAuthentication();
		 Optional<User> userData = userRepository.findById(principal.getName()) ;
		 return userData.map(user -> {
			 UserViewResponse userViewResponse = new UserViewResponse();
			 userViewResponse.setFirstName(user.getFirstName());
			 userViewResponse.setLastName(user.getLastName());
			 userViewResponse.setMailId(user.getMailId());
			 userViewResponse.setUserName(user.getUserName());
			 return userViewResponse;
		 }).orElseThrow(()-> new ResourceNotFoundException("User not present"));
	}

	@Override
	public String test() {
		return "welcome pradeep";
	}

}
