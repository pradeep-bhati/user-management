package com.iqvia.myapplication.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.iqvia.myapplication.validations.ValidFirstName;
import com.iqvia.myapplication.validations.ValidLastName;
import com.iqvia.myapplication.validations.ValidPassword;
import com.iqvia.myapplication.validations.ValidUserName;

public class CreateUserRequest {
	
	@ValidUserName
	private String userName;	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@ValidFirstName
	private String firstName;
	@ValidLastName
	private String lastName;
	
	@Email(message = "email not valid")
	private String mailId;
	@ValidPassword
	private String password;

}
