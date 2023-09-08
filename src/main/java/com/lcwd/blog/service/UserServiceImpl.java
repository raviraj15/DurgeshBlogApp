package com.lcwd.blog.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.blog.entity.User;
import com.lcwd.blog.exception.ResourceNotFoundException;
import com.lcwd.blog.payload.UserDto;
import com.lcwd.blog.repository.UserRepo;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User map = mapper.map(userDto, User.class);
		User savedUser = repo.save(map);
		UserDto user = mapper.map(savedUser, UserDto.class);
		return user;
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {

		User ExistingUser = repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with given id " + userId));

		ExistingUser.setAbout(userDto.getAbout());
		ExistingUser.setEmail(userDto.getEmail());
		ExistingUser.setName(userDto.getName());
		ExistingUser.setPassword(userDto.getPassword());

		User save = repo.save(ExistingUser);

		UserDto map = mapper.map(save, UserDto.class);
		return map;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User ExistingUser = repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with given id " + userId));

		User user = repo.findById(userId).get();
		UserDto map = mapper.map(user, UserDto.class);
		return map;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> findAll = repo.findAll();

		List<UserDto> collect = findAll.stream().map(u -> mapper.map(u, UserDto.class)).collect(Collectors.toList());

		return collect;
	}

	@Override
	public void deleteUser(Integer userId) {
		User ExistingUser = repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with given id " + userId));

		repo.delete(ExistingUser);

	}

}
