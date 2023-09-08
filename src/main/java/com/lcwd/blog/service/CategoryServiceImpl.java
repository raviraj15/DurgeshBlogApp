package com.lcwd.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.blog.entity.Category;
import com.lcwd.blog.exception.ResourceNotFoundException;
import com.lcwd.blog.payload.CategoryDto;
import com.lcwd.blog.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryServiceI {

	@Autowired
	private CategoryRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {

		Category map = mapper.map(categoryDto, Category.class);
		Category save = repo.save(map);
		CategoryDto map2 = mapper.map(save, CategoryDto.class);
		return map2;
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {

		Category existingCategory = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException());

		existingCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());

		Category save = repo.save(existingCategory);
		CategoryDto map = mapper.map(save, CategoryDto.class);
		return map;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category existingCategory = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException());
		CategoryDto map = mapper.map(existingCategory, CategoryDto.class);
		return map;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> findAll = repo.findAll();
		List<CategoryDto> collect = findAll.stream().map(category -> mapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category existingCategory = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException());
		repo.delete(existingCategory);
    
	}

}
