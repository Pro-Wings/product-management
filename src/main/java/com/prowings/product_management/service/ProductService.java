package com.prowings.product_management.service;

import java.util.List;

import com.prowings.product_management.entity.Product;

public interface ProductService {
	
	public Product getProductById(long id);

	public List<Product> getAllProducts();

	public Product saveProduct(Product product);

	public Product updateProduct(Product product);
	
	public void deleteProductById(long id);

	public List<Product> searchProducts();

	public List<Product> getAllProductsSortBy();
	
	
}
