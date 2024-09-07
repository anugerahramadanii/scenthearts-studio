package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCategoryDTO {
private Long id;
private String name;
private String image_path;
private Boolean active;
private Long user_id;
}
