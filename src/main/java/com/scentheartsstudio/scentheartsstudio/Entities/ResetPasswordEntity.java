package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_reset_password")
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOld_password() {
        return this.old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return this.new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getReset_for() {
        return this.reset_for;
    }

    public void setReset_for(String reset_for) {
        this.reset_for = reset_for;
    }

}
