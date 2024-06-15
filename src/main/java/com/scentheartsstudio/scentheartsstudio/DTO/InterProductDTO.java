package com.scentheartsstudio.scentheartsstudio.DTO;

public interface InterProductDTO {
	Long getId();
	String getName();
	String getDescription();
	String getImagePath();
	Double getRealPrice();
	Double getDiscountPrice();
	Integer getStock();
	Long getCategoryId();
	String getCategoryName();
}
