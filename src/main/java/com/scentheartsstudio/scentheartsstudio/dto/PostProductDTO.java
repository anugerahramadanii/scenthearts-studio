package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostProductDTO {
	private Long id;
	private Long category_id;
	private String product_name;
	private Double price;
	private List<String> image_path;
	private String description;
	private Boolean active;
	private Long user_id;
}
