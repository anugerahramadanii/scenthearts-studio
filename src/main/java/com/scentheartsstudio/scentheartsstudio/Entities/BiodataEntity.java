package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "m_biodata")
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


    // @Column(length = 255)
    // private String address;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile_phone() {
        return this.mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    // public String getAddress() {
    // return this.address;
    // }

    // public void setAddress(String address) {
    // this.address = address;
    // }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
