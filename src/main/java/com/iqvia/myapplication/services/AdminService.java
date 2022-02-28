package com.iqvia.myapplication.services;

import com.iqvia.myapplication.dtos.EnableDisableRequest;
import com.iqvia.myapplication.dtos.RoleRequest;
import com.iqvia.myapplication.dtos.UpdateUserDetailsRequest;
import com.iqvia.myapplication.dtos.UserDetailsResponse;
import com.iqvia.myapplication.entites.User;

public interface AdminService {
	
	public void updateRole(RoleRequest roleRequest);
	
	public void updateUserByAdmin(UpdateUserDetailsRequest userDetails);
	
	public void enableDisable(EnableDisableRequest enableDisableRequest);
	
	public UserDetailsResponse getUser(String userName);
	
	public void deleteUser(String username);

}
