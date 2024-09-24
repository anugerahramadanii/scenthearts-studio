package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostProductSizeDTO {
	private Long id;
	private Long product_id;
	private String size_name;
	private Integer stock;
	private Boolean active;
	private Long user_id;
}
