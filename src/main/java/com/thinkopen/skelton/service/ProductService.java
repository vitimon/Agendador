package com.thinkopen.skelton.service;

import java.util.List;
import com.thinkopen.skelton.model.Product;


/**
 * Service interface for Product model
 * JsfGenerator: version 1.0
 */

public interface ProductService {
	
	public List<Product> getAllProducts();

	public Product getProductById(java.math.BigDecimal id);

	public void createOrEditProduct(Product entity);

	public void deleteProduct(Product entity);

}
