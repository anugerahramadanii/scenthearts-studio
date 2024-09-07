package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSizeDTO {
	private Long id;
	private Long product_id;
	private String name;
	private Integer quantity;
	private Boolean active;
}
