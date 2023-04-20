package com.thinkopen.skelton.dao.impl;

import org.springframework.stereotype.Repository;
import com.thinkopen.skelton.dao.ProductDao;
import com.thinkopen.skelton.model.Product;

/**
 * Dao implementation for ProductDao
 * JsfGenerator: version 1.0
 */

@Repository(value="productDao")
public class ProductDaoImpl extends GenericDaoImpl<Product, java.math.BigDecimal> implements ProductDao{

	public ProductDaoImpl() {
		super(Product.class);
	}
}
