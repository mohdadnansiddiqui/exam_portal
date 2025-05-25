package com.examportal.serviceimpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dto.CategoryDto;
import com.examportal.entity.exam.Category;
import com.examportal.repository.CategoryRepository;
import com.examportal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		CategoryDto dto = new CategoryDto();
		BeanUtils.copyProperties(categoryDto, category);
		BeanUtils.copyProperties(categoryRepository.save(category), dto);

		return dto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		Category category = new Category();
		CategoryDto dto = new CategoryDto();
		BeanUtils.copyProperties(categoryDto, category);
		BeanUtils.copyProperties(categoryRepository.save(category), dto);

		return dto;
	}

	@Override
	public Set<CategoryDto> getAllCategories() {
		Set<Category> categories = categoryRepository.findAll().stream().collect(Collectors.toSet());
		Set<CategoryDto> category = new HashSet<>();
		categories.forEach(c -> {
			CategoryDto categoryDto = new CategoryDto();
			BeanUtils.copyProperties(c, categoryDto);
			category.add(categoryDto);
		});

		System.out.println("size==>" + category.size());
		return category;
	}

	@Override
	public CategoryDto getCategory(Long id) {
		CategoryDto categoryDto = new CategoryDto();
		BeanUtils.copyProperties(categoryRepository.findById(id).get(), categoryDto);

		return categoryDto;
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);

	}

}
