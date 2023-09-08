package com.lcwd.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
