package com.scentheartsstudio.scentheartsstudio.DTO;

public interface InterProductDTO {
	Long getId();
	String getProduct_name();
	String getDescription();
	String getImage_path();
	Double getReal_price();
	Double getDiscount_rate();
	Double getDiscount_price();
	Integer getStock();
	Long getCategory_id();
	String getCategory_name();
}
