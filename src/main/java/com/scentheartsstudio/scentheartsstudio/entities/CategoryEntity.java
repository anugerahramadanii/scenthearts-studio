package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_category")
@Getter
@Setter
public class CategoryEntity extends BaseProperties {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String image_path;

//    @Lob
//    @Column
//    private byte[] image;

    @Column(nullable = false)
    private Boolean active;
}
