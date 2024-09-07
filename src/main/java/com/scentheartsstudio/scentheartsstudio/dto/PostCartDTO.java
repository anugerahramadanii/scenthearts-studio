package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCartDTO {
	private Long id;
	private Long user_id;
	private Long product_id;
	private Long product_size_id;
	private Integer quantity;
	private Double product_price;
	private Double total_price;
}
