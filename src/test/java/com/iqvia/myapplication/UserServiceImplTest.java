package com.iqvia.myapplication;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.iqvia.myapplication.dtos.CreateUserRequest;
import com.iqvia.myapplication.entites.User;
import com.iqvia.myapplication.repositories.UserRepository;
import com.iqvia.myapplication.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	
	@Mock 
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	UserServiceImpl userServiceImpl ;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	void testRegisterUser() {
		CreateUserRequest userRequest = new CreateUserRequest();
		userRequest.setFirstName("pradeep");
		userRequest.setLastName("bhati");
		userRequest.setMailId("er.pradeep47@gmail.com");
		userRequest.setPassword("Pass@or1");
		userRequest.setUserName("janish");
		Mockito.doNothing().when(userRepository).existsById(Mockito.anyString());
		Mockito.doNothing().when(userRepository).save(Mockito.any(User.class));
		Mockito.verify(userServiceImpl, Mockito.times(1)).registerUser(userRequest);
	}

	@Test
	void testViewUser() {
		fail("Not yet implemented");
	}

	@Test
	void testTest() {
		fail("Not yet implemented");
	}

}
