package com.iqvia.myapplication.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iqvia.myapplication.dtos.CreateUserRequest;
import com.iqvia.myapplication.dtos.EnableDisableRequest;
import com.iqvia.myapplication.dtos.RoleRequest;
import com.iqvia.myapplication.dtos.UpdateUserDetailsRequest;
import com.iqvia.myapplication.dtos.UserDetailsResponse;
import com.iqvia.myapplication.services.AdminService;
import com.iqvia.myapplication.services.UserService;
import com.iqvia.myapplication.validations.ValidUserName;

import io.swagger.annotations.*;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "UserRestController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	

	@PostMapping("register/user")
	@ApiOperation(value = "Register User in the System", response = String.class)
	public ResponseEntity<?>  registerUser(
			 @RequestBody @Valid CreateUserRequest userRequest) {
			userService.registerUser(userRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}
	
	@PostMapping("admin/update/role")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> registerUser(@RequestBody @Valid RoleRequest roleRequest) {
		adminService.updateRole(roleRequest);
		return ResponseEntity.status(HttpStatus.OK).body("Role updated successfully");
	}
	
	@PostMapping("admin/update/userdetails")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateUserByAdmin(@RequestBody @Valid UpdateUserDetailsRequest userDetails) {
		adminService.updateUserByAdmin(userDetails);
		return ResponseEntity.status(HttpStatus.OK).body("User details updated successfully");
		
	}
	
	@PostMapping("/admin/enable/user")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> enableDisableUser(@Valid @RequestBody EnableDisableRequest enableDisableRequest) {
		adminService.enableDisable(enableDisableRequest);
		return ResponseEntity.status(HttpStatus.OK).body("User status updated successfully");
	}
		
	@GetMapping(value="/admin/get/user/{username}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDetailsResponse getUser(@PathVariable @ValidUserName String username) {
		return adminService.getUser(username);
	}
	
	@GetMapping("/test/security")
	@Secured("ROLE_ADMIN")
	public String test() {
		return userService.test();
	}
	
	@DeleteMapping("/admin/delete/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable @ValidUserName String username) {
		 adminService.deleteUser(username);
		 return ResponseEntity.status(HttpStatus.OK).body("User  deleted successfully");
	}
	
	@GetMapping("/view/user/details")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> viewUser(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.viewUser());
	}
	
//	@GetMapping(value = "/username")
//	@ResponseBody
//	public String currentUserName(Authentication authentication) {
//	     return authentication.getName();
//	}
}
