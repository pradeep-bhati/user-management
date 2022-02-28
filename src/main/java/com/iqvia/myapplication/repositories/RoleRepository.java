package com.iqvia.myapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iqvia.myapplication.entites.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	
	public Optional<Role> findByroles(String roles);
	
	@Query(value = "select r from Role r where r.roles = :role AND r.user.userName = :username")
	public Role checkRoles(@Param("role")String role,@Param("username") String username);

}
