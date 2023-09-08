package com.lcwd.blog.service;

import java.util.List;

import com.lcwd.blog.payload.UserDto;

public interface UserServiceI {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(Integer userId,UserDto userDto);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
