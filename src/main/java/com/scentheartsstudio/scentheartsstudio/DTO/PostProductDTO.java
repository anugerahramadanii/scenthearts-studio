package com.scentheartsstudio.scentheartsstudio.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostProductDTO {
	private Long id;
	private Long category_id;
	private String product_name;
	private String product_size_name;
	private String description;
	private List<String> image_path;
	private Long product_size_id;
	private Integer quantity;
	private Double real_price;
	private Double discount_rate;
	private Double discount_price;
	private Boolean active;
	private Long user_id;
}
