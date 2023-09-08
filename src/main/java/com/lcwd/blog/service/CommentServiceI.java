package com.lcwd.blog.service;


import com.lcwd.blog.payload.CommentDto;

public interface CommentServiceI {
	
	CommentDto addComment(CommentDto commentDto,Integer postId);
	
	void deleteCommentById(Integer CommentId);
	

}
