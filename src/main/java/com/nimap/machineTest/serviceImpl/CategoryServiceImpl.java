package com.nimap.machineTest.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimap.machineTest.model.Category;
import com.nimap.machineTest.repository.CategoryRepository;
import com.nimap.machineTest.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	 @Override
	    public Category getCategoryById(Long id) {
	        return categoryRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
	    }


	 @Override
	    public void deleteCategory(Long id) {
		 
		 if(!categoryRepository.existsById(id)) {
			 throw new RuntimeException("category not found with id: "+id);
		 }
	        categoryRepository.deleteById(id);
	    }

	  @Override
	    public List<Category> getAllCategory(Integer pageNumber, Integer pageSize) {
	        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
	        return categoryRepository.findAll(pageRequest).getContent();
	    }

		@Override
		public Category updateCategory(Long id, Category updatedCategory) {
			Category existingCategory=categoryRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
			
			existingCategory.setName(updatedCategory.getName());
			
			return categoryRepository.save(existingCategory);
		}
	 
}
