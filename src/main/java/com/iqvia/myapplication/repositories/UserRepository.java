package com.iqvia.myapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqvia.myapplication.entites.User;

public interface UserRepository extends JpaRepository<User,String>{

}
