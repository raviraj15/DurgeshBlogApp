package com.lcwd.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.blog.entity.Comment;
import com.lcwd.blog.entity.Post;
import com.lcwd.blog.exception.ResourceNotFoundException;
import com.lcwd.blog.payload.CommentDto;
import com.lcwd.blog.repository.CommentRepo;
import com.lcwd.blog.repository.PostRepo;

@Service
public class CommentServiceImpl implements  CommentServiceI{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDto addComment(CommentDto commentDto,Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException());
		Comment comment = mapper.map(commentDto, Comment.class);
		comment.setCommentPost(post);
		Comment save = commentRepo.save(comment);
		CommentDto map = mapper.map(save, CommentDto.class);
		return map;
	}

	@Override
	public void deleteCommentById(Integer CommentId) {
		Comment comment = commentRepo.findById(CommentId).orElseThrow(()-> new ResourceNotFoundException());
		
		commentRepo.delete(comment);
	}

}
