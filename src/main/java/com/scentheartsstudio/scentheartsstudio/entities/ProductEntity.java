package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_product")
@Getter
@Setter
public class ProductEntity extends BaseProperties {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long category_id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String image_path;

    @Column(nullable = false, length = 18, precision = 2)
    private Double price;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean active;
}
