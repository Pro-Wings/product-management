package com.prowings.product_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.product_management.entity.Product;

@RestController
public class ProductController {
	
	@GetMapping("/products/{id}")
	public Product getSampleProduct(@PathVariable int id)
	{
		return new Product(111L, "iPhone", "Smart Mobile Phone", 130000, "electronics");
	}

}
