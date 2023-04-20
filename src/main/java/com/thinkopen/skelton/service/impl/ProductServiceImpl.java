package com.thinkopen.skelton.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thinkopen.skelton.dao.ProductDao;
import com.thinkopen.skelton.model.Product;
import com.thinkopen.skelton.service.ProductService;

/**
 * Service interface implementation for Product model
 * JsfGenerator: version 1.0
 */


@Service(value="productService")
public class ProductServiceImpl implements ProductService{

	//--------------------------------------------------------------
	@Autowired
	private ProductDao 	productDao;
	//--------------------------------------------------------------
	

	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	public void createOrEditProduct(Product entity) {
		productDao.saveOrUpdate(entity);
	}

	public Product getProductById(java.math.BigDecimal id) {		
		return productDao.find(id);
	}

	public void deleteProduct(Product entity) {		
		productDao.delete(entity);
	}

	//--------------------------------------------------------------
	//	Getters and setters
	//--------------------------------------------------------------
	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}
