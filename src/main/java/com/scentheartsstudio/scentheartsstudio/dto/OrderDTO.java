package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
	private Long user_id;
	private String shipping_address;
	private int weight;  // Weight in grams
	private String courier;
	private List<OrderItemDTO> items;
}
