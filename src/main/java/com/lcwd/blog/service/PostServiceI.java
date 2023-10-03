package com.lcwd.blog.service;

import java.util.List;

import com.lcwd.blog.payload.PostDto;


public interface PostServiceI {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	List<PostDto> getPostsByUserId(Integer userId);

	List<PostDto> getPostsByCategoryId(Integer categoryId);
	

	PostDto updatePost(Integer postId, PostDto postDto);

	PostDto getPostById(Integer postId);

	//List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize);

	void deletePost(Integer postId);

	// search post by any keyword

	List<PostDto> searchPost(String keyword);



}
