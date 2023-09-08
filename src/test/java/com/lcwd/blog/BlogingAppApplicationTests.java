package com.lcwd.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.blog.repository.UserRepo;
import com.lcwd.blog.service.UserServiceI;

@SpringBootTest
class BlogingAppApplicationTests {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private UserServiceI service;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void RepoTest()
	{
		String name = repo.getClass().getName();
		String packageName = repo.getClass().getPackageName();
		System.out.println(name);
		System.out.println(packageName);
	}
	
	
	@Test
	public void serviceTest()
	{
		String name = service.getClass().getName();
		String packageName = service.getClass().getPackageName();
		System.out.println(name);
		System.out.println(packageName);
	}

}
