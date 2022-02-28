package com.iqvia.myapplication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iqvia.myapplication.dtos.UpdateUserDetailsRequest;
import com.iqvia.myapplication.dtos.UserDetailsResponse;
import com.iqvia.myapplication.dtos.EnableDisableRequest;
import com.iqvia.myapplication.dtos.RoleRequest;
import com.iqvia.myapplication.entites.Role;
import com.iqvia.myapplication.entites.User;
import com.iqvia.myapplication.exceptions.ResourceNotFoundException;
import com.iqvia.myapplication.repositories.AdminRepository;
import com.iqvia.myapplication.repositories.RoleRepository;
import com.iqvia.myapplication.repositories.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void updateRole(RoleRequest roleRequest) {

		Optional<User> userRoleToUpdate = userRepository.findById(roleRequest.getUsername());
		
		userRoleToUpdate.orElseThrow(()-> new ResourceNotFoundException("user not present"));
		userRoleToUpdate.ifPresent(user -> { 
			roleRequest.getRoles().stream().forEach(role -> {
				Role alreadyExistingRole = roleRepository.checkRoles(role,user.getUserName());
				if(alreadyExistingRole == null) {
				Role newRole = new Role();
				newRole.setRoles(role);
				newRole.setUser(user);
				roleRepository.save(newRole);}
			
			});
		});		
	}

	@Override
	public void updateUserByAdmin(UpdateUserDetailsRequest userDetails) {
		Optional<User> userToUpdate = userRepository.findById(userDetails.getUserName());
		userToUpdate.orElseThrow(()-> new ResourceNotFoundException("User not present"));
		userToUpdate.ifPresent(user ->{
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			user.setMailId(userDetails.getMailId());
			userRepository.save(user);
		});

	}

	@Override
	public void enableDisable(EnableDisableRequest enableDisableRequest) {
		Optional<User> userData = userRepository.findById(enableDisableRequest.getUserName());
		userData.orElseThrow(()-> new ResourceNotFoundException("User not present"));
		userData.ifPresent(user ->{
			user.setEnabled(enableDisableRequest.getStatus());
			userRepository.save(user);
		});
	}

	//exception to be added here
	@Override
	public UserDetailsResponse getUser(String userName) {
		Optional<User> userData = userRepository.findById(userName);
		return userData.map(user->{
			return new UserDetailsResponse(user);
		}).
		orElseThrow(()-> new ResourceNotFoundException("User not present"));
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		userRepository.deleteById(username);

	}

}
