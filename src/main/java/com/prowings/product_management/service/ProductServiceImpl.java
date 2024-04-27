package com.prowings.product_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.product_management.dao.ProductRepository;
import com.prowings.product_management.entity.Product;
import com.prowings.product_management.exception.InvalidProductException;
import com.prowings.product_management.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository; 
	
	
	@Override
	public Product getProductById(long id) {
		
		Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            throw new ProductNotFoundException("Product of specified ID is not present in System!!");
		
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		Product fetchedProduct = null;
		if(validProduct(product))
			fetchedProduct = productRepository.save(product);
		return fetchedProduct;
		
	}

	private boolean validProduct(Product product) {
		
		if(product.getName().length() < 5)
			throw new InvalidProductException("Product Name is not valid!!");
		else if(product.getPrice()<100)
			throw new InvalidProductException("Product price must be greater than 100");
		else 
			return true;

		
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProductById(long id) {
		
		productRepository.deleteById(id);

	}

	@Override
	public List<Product> searchProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProductsSortBy() {
		// TODO Auto-generated method stub
		return null;
	}

}
