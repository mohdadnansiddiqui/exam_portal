package com.examportal.service;

import java.util.Set;

import com.examportal.dto.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto);

	Set<CategoryDto> getAllCategories();

	CategoryDto getCategory(Long id);

	void deleteCategory(Long id);

}
