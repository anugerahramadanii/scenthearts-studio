package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_product")
public class ProductEntity extends BaseProperties {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long category_id;

    @Column
    private Long product_size_id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String image_path;

    @Column
    private String description;

    @Column
    private Integer quantity;

    @Column(nullable = false, length = 18, precision = 2)
    private Double real_price;

    @Column
    private Double discount_rate;

    @Column(nullable = false, length = 18, precision = 2)
    private Double discount_price;

    @Column(nullable = false)
    private Boolean active;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getProduct_size_id() {
        return product_size_id;
    }

    public void setProduct_size_id(Long product_size_id) {
        this.product_size_id = product_size_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getReal_price() {
        return real_price;
    }

    public void setReal_price(Double real_price) {
        this.real_price = real_price;
    }

    public Double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(Double discount_rate) {
        this.discount_rate = discount_rate;
    }

    public Double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(Double discount_price) {
        this.discount_price = discount_price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
