package com.iqvia.myapplication.security.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iqvia.myapplication.entites.User;
import com.iqvia.myapplication.repositories.UserRepository;

@Service
public class CurrentUserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userData = userRepository.findById(username);
		CurrentUser currentuser = userData.map(user -> new CurrentUser(user)).orElseThrow(() -> new UsernameNotFoundException("user not present"));
		return currentuser;
	}

}
