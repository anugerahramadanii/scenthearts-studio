package com.scentheartsstudio.scentheartsstudio.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "m_user")
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBiodata_id() {
        return this.biodata_id;
    }

    public void setBiodata_id(Long biodata_id) {
        this.biodata_id = biodata_id;
    }

    public Long getRole_id() {
        return this.role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Integer getLogin_attempt() {
        return this.login_attempt;
    }

    public void setLogin_attempt(Integer login_attempt) {
        this.login_attempt = login_attempt;
    }

    public Boolean isIs_locked() {
        return this.is_locked;
    }

    public Boolean getIs_locked() {
        return this.is_locked;
    }

    public void setIs_locked(Boolean is_locked) {
        this.is_locked = is_locked;
    }

    public Date getLast_login() {
        return this.last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

}
