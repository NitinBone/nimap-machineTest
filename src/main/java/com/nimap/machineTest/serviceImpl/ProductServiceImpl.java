package com.nimap.machineTest.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimap.machineTest.model.Category;
import com.nimap.machineTest.model.Product;
import com.nimap.machineTest.repository.CategoryRepository;
import com.nimap.machineTest.repository.ProductRepository;
import com.nimap.machineTest.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	 @Override
	    public Product getProductById(Long id) {
	        return productRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
	    }
	
	 @Override
	    public Product updateProduct(Long id, Product updatedProduct) {
	  
	        Product existingProduct = productRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

	        existingProduct.setName(updatedProduct.getName());
	        existingProduct.setPrice(updatedProduct.getPrice());

	        if (updatedProduct.getCategory() != null) {
	            Category category = categoryRepository.findById(updatedProduct.getCategory().getId())
	                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + updatedProduct.getCategory().getId()));
	            existingProduct.setCategory(category);
	        }

	        return productRepository.save(existingProduct);
	    }
	 
	 @Override
	    public void deleteProductById(Long id) {
	        if (!productRepository.existsById(id)) {
	            throw new RuntimeException("Product not found with id: " + id);
	        }
	        productRepository.deleteById(id);
	    }



	@Override
	public List<Product> getAllProduct(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
		return productRepository.findAll(pageRequest).getContent();
	}
	
	 @Override
	    public Product createProduct(Product product) {
	        Category category = categoryRepository.findById(product.getCategory().getId())
	                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + product.getCategory().getId()));

	        product.setCategory(category);
	        return productRepository.save(product);
	    }
}
