package com.lcwd.blog.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	//Optional<User> findByEmail(String username);
	User findByName(String username);

}
