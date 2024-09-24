package com.scentheartsstudio.scentheartsstudio.entities;

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
@Table(name = "t_token")
@Getter
@Setter
public class TokenEntity extends BaseProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String token;

    @Column
    private Date expired_on;

    @Column
    private Boolean is_expired;

    @Column(length = 20)
    private String used_for;
}
