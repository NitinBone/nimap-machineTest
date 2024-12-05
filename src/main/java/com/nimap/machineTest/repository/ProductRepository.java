package com.nimap.machineTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimap.machineTest.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
