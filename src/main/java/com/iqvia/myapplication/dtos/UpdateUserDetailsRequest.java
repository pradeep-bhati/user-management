package com.iqvia.myapplication.dtos;

import javax.validation.constraints.Email;

import com.iqvia.myapplication.validations.ValidFirstName;
import com.iqvia.myapplication.validations.ValidLastName;
import com.iqvia.myapplication.validations.ValidUserName;

public class UpdateUserDetailsRequest {
	@ValidUserName
	private String userName;
	@ValidFirstName
	private String firstName;
	@ValidLastName
	private String lastName;
	@Email(message = "email not valid")
	private String mailId;
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

}
