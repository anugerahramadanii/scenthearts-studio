package com.scentheartsstudio.scentheartsstudio.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "m_user")
@Getter
@Setter
public class UserEntity extends BaseProperties {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long biodata_id;

    @Column
    private Long role_id;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String password;

    @Column
    private Integer login_attempt;

    @Column
    private Boolean is_locked;

    @Column
    private Date last_login;
}
