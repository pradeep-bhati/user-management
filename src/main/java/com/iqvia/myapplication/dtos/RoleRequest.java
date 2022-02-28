package com.iqvia.myapplication.dtos;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.iqvia.myapplication.validations.ValidUserName;

public class RoleRequest {
	@ValidUserName
	private String username;
	
	
	private Set< @NotBlank(message = "Role can not be blank.") 
	@Pattern(regexp = "^(ROLE_[A-Z]+$)", message = "Role should be in ROLE_ADMIN format") String> roles;

	
	public String getUsername() {
		return username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
