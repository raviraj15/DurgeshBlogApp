package com.lcwd.blog.payload;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.lcwd.blog.entity.Category;
import com.lcwd.blog.entity.Comment;
import com.lcwd.blog.entity.Post;
import com.lcwd.blog.entity.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class PostDto {

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private UserDto user;

	private CategoryDto category;
	
	private Set<CommentDto> set=new HashSet<>();
}
