package com.iqvia.myapplication;

import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iqvia.myapplication.controller.UserController;

@SpringBootTest
class UserManagementApplicationTests {
	
	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		Assert.assertNotNull(userController);
	}

}
