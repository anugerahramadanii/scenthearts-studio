package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_reset_password")
@Getter
@Setter
public class ResetPasswordEntity extends BaseProperties {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String old_password;

    @Column(length = 255)
    private String new_password;

    @Column(length = 20)
    private String reset_for;

}
