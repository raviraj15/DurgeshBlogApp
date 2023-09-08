package com.lcwd.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
