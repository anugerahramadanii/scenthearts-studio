package com.scentheartsstudio.scentheartsstudio.dto;

public interface InterProductDTO {
	Long getId();
	String getProduct_name();
	Long getProduct_size_id();
	String getSize_name();
	String getDescription();
	String getImage_path();
	String getSizes_with_stock();
	Integer getStock();
	Double getPrice();
	Long getCategory_id();
	String getCategory_name();
}
