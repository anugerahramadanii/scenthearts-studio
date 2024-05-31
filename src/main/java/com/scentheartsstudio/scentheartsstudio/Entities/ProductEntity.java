package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_product")
public class ProductEntity extends BaseProperties {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long category_id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private String description;

    @Column(nullable = false, length = 18, precision = 2)
    private Double real_price;


    @Column(nullable = false, length = 18, precision = 2)
    private Double discount_price;

    @Column(nullable = false, length = 18, precision = 2)
    private Double stock;

    @Column(nullable = false)
    private Boolean active;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   public Double getReal_price() {
        return this.real_price;
    }

    public void setReal_price(Double real_price) {
        this.real_price = real_price;
    }

    public Double getDiscount_price() {
        return this.discount_price;
    }

    public void setDiscount_price(Double discount_price) {
        this.discount_price = discount_price;
    }

    public Double getStock() {
        return this.stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Boolean isActive() {
        return this.active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
