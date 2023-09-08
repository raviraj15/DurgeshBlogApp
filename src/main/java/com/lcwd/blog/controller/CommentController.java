package com.lcwd.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.blog.payload.ApiResponse;
import com.lcwd.blog.payload.CommentDto;
import com.lcwd.blog.payload.PostDto;
import com.lcwd.blog.service.CommentServiceI;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentServiceI service;

	@PostMapping("/addComment/{postId}")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		CommentDto addComment = service.addComment(commentDto, postId);
		return new ResponseEntity<>(addComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteComment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		service.deleteCommentById(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully", true), HttpStatus.OK);
	}

}
