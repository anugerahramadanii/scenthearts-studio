package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_biodata")
@Getter
@Setter
public class BiodataEntity extends BaseProperties {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String firstname;

    @Column(length = 255)
    private String lastname;

    @Column(length = 15)
    private String mobile_phone;

    @Lob
    private byte[] image;

    @Column(length = 255)
    private String image_path;
}
