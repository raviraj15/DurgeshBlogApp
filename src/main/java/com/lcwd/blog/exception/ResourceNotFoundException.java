package com.lcwd.blog.exception;

public class ResourceNotFoundException extends RuntimeException{

	
	public ResourceNotFoundException()
	{
		super("user not find with given id");
	}
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
}
