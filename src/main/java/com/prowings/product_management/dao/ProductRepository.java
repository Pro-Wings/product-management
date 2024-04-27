package com.prowings.product_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prowings.product_management.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
