package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
	private Long product_id;
	private Long product_size_id;
	private Integer quantity;
	private Double price;
}
