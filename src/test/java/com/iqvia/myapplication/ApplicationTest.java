package com.iqvia.myapplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqvia.myapplication.dtos.CreateUserRequest;
import com.iqvia.myapplication.dtos.RoleRequest;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

public class ApplicationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void testRegisterUser() throws Exception{
		CreateUserRequest content = new CreateUserRequest();
		content.setFirstName("pradeep");
		content.setLastName("bhati");
		content.setMailId("er.pradeep47@gmail.com");
		content.setUserName("pradeep");
		content.setPassword("Pass@or1");
		ObjectMapper mapper = new ObjectMapper();
		String content1 = mapper.writeValueAsString(content);
		mockMvc.perform(post("/register/user").contentType(MediaType.APPLICATION_JSON).content(content1))
		.andExpect(status().isCreated()).andExpect(content().string("User registered successfully"));
		
	}
	
	@Test
	@WithMockUser(setupBefore = TestExecutionEvent.TEST_EXECUTION, username="lanish",authorities="ROLE_ADMIN",password = "Pass@or1")
	@Sql(scripts = "classpath:test-scripts/test-data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = {"classpath:test-scripts/clear-data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateRole() throws Exception{
		RoleRequest rolerequest = new RoleRequest();
		rolerequest.setUsername("voot");
		rolerequest.setRoles(new HashSet<>(Arrays.asList("ROLE_SUPER", "ROLE_DUPER")));
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(rolerequest);
		mockMvc.perform(post("/admin/update/role").contentType(MediaType.APPLICATION_JSON)
				.content(content)).andExpect(status().isOk()).andExpect(content().string("Role updated successfully"));
		
	}

}
