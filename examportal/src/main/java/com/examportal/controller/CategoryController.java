package com.examportal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.bean.ApiResponse;
import com.examportal.dto.CategoryDto;
import com.examportal.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@PostMapping("/create")
	private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

		return new ResponseEntity<CategoryDto>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	private ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {

		return new ResponseEntity<CategoryDto>(this.categoryService.updateCategory(categoryDto), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {

		return new ResponseEntity<CategoryDto>(this.categoryService.getCategory(id), HttpStatus.OK);
	}

	@GetMapping("")
	private ResponseEntity<?> getCategories() {

		return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted"), HttpStatus.OK);
	}

}
