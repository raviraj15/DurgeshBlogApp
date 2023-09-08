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
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.blog.payload.ApiResponse;
import com.lcwd.blog.payload.CategoryDto;
import com.lcwd.blog.payload.UserDto;
import com.lcwd.blog.service.CategoryServiceI;
import com.lcwd.blog.service.UserServiceI;


@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryServiceI service;

	@PostMapping("/addCategory")
	public ResponseEntity<CategoryDto> addCategory(@javax.validation.Valid @RequestBody CategoryDto CategoryDto) {
		 CategoryDto addCategory = service.addCategory(CategoryDto);
		return new ResponseEntity<>(addCategory, HttpStatus.CREATED);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
		 CategoryDto categoryById = service.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryById, HttpStatus.OK);
	}

	@GetMapping("/getAllcategories")
	public ResponseEntity<List<CategoryDto>> getAllcategories() {
		List<CategoryDto> allCategories = service.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(allCategories, HttpStatus.OK);
	}

	@PutMapping("/updateCategories/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategories(@Valid @PathVariable Integer categoryId, 
			@RequestBody CategoryDto CategoryDto) {
		CategoryDto updateCategory = service.updateCategory(categoryId, CategoryDto);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer categoryId) {
		service.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully", true), HttpStatus.OK);
	}
}
