package com.iqvia.myapplication;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqvia.myapplication.controller.UserController;
import com.iqvia.myapplication.dtos.CreateUserRequest;
import com.iqvia.myapplication.dtos.UserDetailsResponse;
import com.iqvia.myapplication.services.AdminServiceImpl;
import com.iqvia.myapplication.services.UserServiceImpl;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class,excludeAutoConfiguration = SecurityAutoConfiguration.class,
excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = WebSecurityConfigurer.class))
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminServiceImpl adminServiceImpl;
	
	@MockBean
	private UserServiceImpl userServiceImpl;
	
	UserDetailsResponse userDetailResponse = new UserDetailsResponse();
	
	@Before
	public  void init() {
		System.out.println("initializing");
		//userDetailResponse = new UserDetailsResponse();
		userDetailResponse.setAccountNonExpired(true);
		userDetailResponse.setAccountNonLocked(true);
		userDetailResponse.setCredentialsNonExpired(true);
		userDetailResponse.setEnabled(true);
		userDetailResponse.setFirstName("pradeep");
		userDetailResponse.setLastName("bhati");
		userDetailResponse.setMailId("er.pradeep");
		userDetailResponse.setUserName("pradeep");
	}

	@Test
	public void testRegisterUserCreateUserRequest() throws Exception {
		CreateUserRequest userRequest = new CreateUserRequest();
		userRequest.setFirstName("pradeep");
		userRequest.setLastName("bhati");
		userRequest.setMailId("er.pradeep47@gmail.com");
		userRequest.setPassword("Pass@or1");
		userRequest.setUserName("janish");
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(userRequest);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		when(userServiceImpl.registerUser(userRequest)).thenDoNothing();
//		doNothing().when(userServiceImpl).registerUser(userRequest);
		this.mockMvc.perform(post("/register/user").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated())
		.andExpect(content().string("User registered successfully"));
	}
	
	@Test
	public void testRegisterUserCreateUserRequestwithException() throws Exception {
		CreateUserRequest userRequest = new CreateUserRequest();
		userRequest.setFirstName("pradeep");
		userRequest.setLastName("bhati");
		userRequest.setMailId("er.pradeep47@gmail.com");
		userRequest.setPassword("Pass@or");
		userRequest.setUserName("janish");
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(userRequest);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		when(userServiceImpl.registerUser(userRequest)).thenDoNothing();
//		doNothing().when(userServiceImpl).registerUser(userRequest);
		this.mockMvc.perform(post("/register/user").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Input Validation Errors"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value("Password should be of at  least 8 characters containing "
				+ "one lower case alphabet, one upper case alphabet,one numeric and one special character."));

		
	}

	@Test
	public void testRegisterUserRoleRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserByAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnableDisableUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = null;
		try {
			jsonContent = mapper.writeValueAsString(userDetailResponse);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		when(adminServiceImpl.getUser("pradeep")).thenReturn(userDetailResponse);
		this.mockMvc.perform(get("/admin/get/user/pradeep")).andExpect(status().isOk())
		.andExpect(content().json(jsonContent));
		//.andDo(print())
		//.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("pradeep"))
		//.andExpect(MockMvcResultMatchers.jsonPath("$.mailId").value("er.pradeep"));
		
//		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() throws Exception {
		this.mockMvc.perform(delete("/admin/delete/{username}","pradeep")).andExpect(status().isOk())
		.andExpect(content().string("User  deleted successfully"));
	}

	@Test
	public void testViewUser() {
		fail("Not yet implemented");
	}

}
