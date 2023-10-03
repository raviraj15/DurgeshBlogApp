package com.lcwd.blog.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.blog.payload.ApiResponse;
import com.lcwd.blog.payload.UserDto;
import com.lcwd.blog.service.UserServiceI;
import com.lcwd.blog.service.UserServiceImpl;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceI service;
	
	 @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is blog";
    }


	@PostMapping("/addUser")
	public ResponseEntity<UserDto> addUser(@javax.validation.Valid @RequestBody UserDto userDto) {
		UserDto createUser = service.createUser(userDto);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto userById = service.getUserById(userId);
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> allUsers = service.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@javax.validation.Valid @PathVariable Integer userId, @RequestBody UserDto userDto) {
		UserDto updateUser = service.updateUser(userId, userDto);
		return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		service.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
	}
}
