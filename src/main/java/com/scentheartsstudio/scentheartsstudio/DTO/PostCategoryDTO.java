package com.scentheartsstudio.scentheartsstudio.DTO;

public class PostCategoryDTO {
private Long id;
private String name;
private String image_path;
private Boolean active;
private Long user_id;

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return this.name;
}

public void setName(String name) {
    this.name = name;
}

public String getImage_path() {
    return this.image_path;
}

public void setImage_path(String image_path) {
    this.image_path = image_path;
}

public Boolean getActive() {
    return this.active;
}

public void setActive(Boolean active) {
    this.active = active;
}

public Long getUser_id() {
    return this.user_id;
}

public void setUser_id(Long user_id) {
    this.user_id = user_id;
}

}
