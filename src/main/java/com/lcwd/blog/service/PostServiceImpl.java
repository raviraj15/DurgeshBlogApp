package com.lcwd.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lcwd.blog.entity.Category;
import com.lcwd.blog.entity.Post;
import com.lcwd.blog.entity.User;
import com.lcwd.blog.exception.ResourceNotFoundException;
import com.lcwd.blog.payload.CategoryDto;
import com.lcwd.blog.payload.PostDto;
import com.lcwd.blog.payload.PostResponse;
import com.lcwd.blog.repository.CategoryRepo;
import com.lcwd.blog.repository.PostRepo;
import com.lcwd.blog.repository.UserRepo;

@Service
public class PostServiceImpl implements PostServiceI {

	@Autowired
	private PostRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User ExistingUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with given id " + userId));

		Category existingCategory = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException());

		Post map = mapper.map(postDto, Post.class);
		map.setImageName("default.png");
		map.setAddedDate(new Date());
		map.setCategory(existingCategory);
		map.setUser(ExistingUser);
		Post save = repo.save(map);
		PostDto map2 = mapper.map(save, PostDto.class);
		return map2;
	}

	@Override
	public PostDto updatePost(Integer postId, PostDto postDto) {
		Post existingPost = repo.findById(postId).orElseThrow(() -> new ResourceNotFoundException());
		existingPost.setTitle(postDto.getTitle());
		existingPost.setContent(postDto.getContent());
		existingPost.setImageName(postDto.getImageName());
		Post save = repo.save(existingPost);
		PostDto map = mapper.map(save, PostDto.class);
		return map;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post orElseThrow = repo.findById(postId).orElseThrow(() -> new ResourceNotFoundException());
		 PostDto map = mapper.map(orElseThrow, PostDto.class);
		return map;
	}

	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {

		PageRequest of = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

		Page<Post> findAll = repo.findAll(of);
		List<Post> content = findAll.getContent();
		List<PostDto> collect = content.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalElement(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastpage(findAll.isLast());
		return postResponse;
	}

	@Override
	public void deletePost(Integer postId) {
		Post orElseThrow = repo.findById(postId).orElseThrow(() -> new ResourceNotFoundException());
		repo.delete(orElseThrow);

	}

	@Override
	public List<PostDto> getPostsByCategoryId(Integer categoryId) {
		Category existingCategory = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException());

		List<Post> findByCategory = repo.findByCategory(existingCategory);
		List<PostDto> collect = findByCategory.stream().map(cat -> mapper.map(cat, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostsByUserId(Integer userId) {
		User ExistingUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with given id " + userId));

		List<Post> findByUser = repo.findByUser(ExistingUser);
		List<PostDto> collect = findByUser.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> searchByTitle = repo.searchByTitle("%" + keyword + "%");
		List<PostDto> collect = searchByTitle.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

}
