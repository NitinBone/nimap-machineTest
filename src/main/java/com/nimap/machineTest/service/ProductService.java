package com.nimap.machineTest.service;

import java.util.List;
import com.nimap.machineTest.model.Product;

public interface ProductService {

	Product getProductById(Long id);

	Product updateProduct(Long id, Product updatedProduct);

	void deleteProductById(Long id);

	List<Product> getAllProduct(Integer pageNumber, Integer pageSize);

	Product createProduct(Product product);

}
