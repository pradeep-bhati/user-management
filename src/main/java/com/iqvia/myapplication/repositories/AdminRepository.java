package com.iqvia.myapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqvia.myapplication.entites.Role;


public interface AdminRepository extends JpaRepository<Role,String>{

}
