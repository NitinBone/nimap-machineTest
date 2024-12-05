package com.nimap.machineTest.controller;

import java.util.List;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.machineTest.model.Category;
import com.nimap.machineTest.service.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory(
			@RequestParam(value="pageNumber",defaultValue = "1",required = false) Integer pageNumber ,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){
		List<Category> allCategory=categoryService.getAllCategory(pageNumber,pageSize);
		return new ResponseEntity<List<Category>>(allCategory,HttpStatus.OK);
	}

	 @PostMapping
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        Category savedCategory = categoryService.createCategory(category);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	        Category category = categoryService.getCategoryById(id);
	        return ResponseEntity.ok(category);
	    }
	 
	 
	  @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
	        try {
	            Category category = categoryService.updateCategory(id, updatedCategory);
	            return ResponseEntity.ok(category);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategory(id);
	        return ResponseEntity.noContent().build();
	    }
}