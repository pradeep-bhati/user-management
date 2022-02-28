package com.iqvia.myapplication.dtos;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.iqvia.myapplication.validations.ValidUserName;

public class EnableDisableRequest {
	@ValidUserName
	private String userName;
	
	@NotNull(message = "value can be true or false only")
//	@Pattern(regexp = "TRUE|FALSE|true|false")
	private boolean status;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
