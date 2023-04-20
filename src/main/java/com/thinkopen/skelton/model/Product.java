package com.thinkopen.skelton.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2552890499810593970L;

	@Id
	@GeneratedValue
	private BigDecimal id;	
	private String productName;	
	private String productCode;
	private Long availableQty;

	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Long getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(Long availableQty) {
		this.availableQty = availableQty;
	}
	
	
}
