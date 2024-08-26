package com.scentheartsstudio.scentheartsstudio.DTO;

// import com.fasterxml.jackson.annotation.JsonInclude;

public interface InterProductDTO {
	Long getId();
	String getProduct_name();
	String getDescription();
	String getImage_path();
	Integer getQuantity();
	Double getReal_price();
	Double getDiscount_rate();
	Double getDiscount_price();
	Long getCategory_id();
	String getCategory_name();
}
