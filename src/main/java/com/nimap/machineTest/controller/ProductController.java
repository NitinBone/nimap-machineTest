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
import com.nimap.machineTest.model.Product;
import com.nimap.machineTest.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct(
			@RequestParam(value="pageNumber",defaultValue = "1",required = false) Integer pageNumber ,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){
		List<Product> allProducts=productService.getAllProduct(pageNumber,pageSize);
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
	}
	
//	  @PostMapping
//	    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
//	        try {
//	            Product createdProduct = productService.createProduct(productRequestDTO);
//	            ProductResponseDTO responseDTO = new ProductResponseDTO(
//	                createdProduct.getId(),
//	                createdProduct.getName(),
//	                createdProduct.getPrice()
//	            );
//	            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                    .body(null);
//	        }
//	    }
	
	   @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product createdProduct = productService.createProduct(product);
	        return ResponseEntity.ok(createdProduct);
	    }
	  
	  @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Product product = productService.getProductById(id);
	        return ResponseEntity.ok(product);
	    }
	  
	  @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(
	            @PathVariable Long id,
	            @RequestBody Product updatedProduct) {
	        Product product = productService.updateProduct(id, updatedProduct);
	        return ResponseEntity.ok(product);
	    }
	  
	  @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProductById(id);
	        return ResponseEntity.noContent().build();
	    }
}
