package com.lcwd.blog.service;

import java.util.List;

import com.lcwd.blog.payload.CategoryDto;

public interface CategoryServiceI {
	
	CategoryDto addCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto);
	
	CategoryDto getCategoryById(Integer categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(Integer categoryId);

}
