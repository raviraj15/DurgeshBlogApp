package com.lcwd.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.blog.config.AppContants;
import com.lcwd.blog.payload.ApiResponse;
import com.lcwd.blog.payload.CategoryDto;
import com.lcwd.blog.payload.PostDto;
import com.lcwd.blog.payload.PostResponse;
import com.lcwd.blog.payload.UserDto;
import com.lcwd.blog.service.PostServiceI;



@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostServiceI service;

	@PostMapping("/addPost/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = service.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> postsByUserId = service.getPostsByUserId(userId);

		return new ResponseEntity<List<PostDto>>(postsByUserId, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postsByCategoryId = service.getPostsByCategoryId(categoryId);

		return new ResponseEntity<List<PostDto>>(postsByCategoryId, HttpStatus.OK);
	}

	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updateCategories(@Valid @PathVariable Integer postId, @RequestBody PostDto postDto) {
		PostDto updatePost = service.updatePost(postId, postDto);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		service.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/getPostByPostId/{PostId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer PostId) {
		PostDto postById = service.getPostById(PostId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponse> getAll(
			@RequestParam(value = "pageNumber", defaultValue = AppContants.pageNumber, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContants.pageSize, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppContants.sortBy, required = false) String sortBy) {
		PostResponse allPosts = service.getAllPosts(pageNumber, (Integer) pageSize, sortBy);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}

	@GetMapping("post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
		List<PostDto> searchPost = service.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);

	}

}
