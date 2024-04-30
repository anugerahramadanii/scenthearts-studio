package com.scentheartsstudio.scentheartsstudio.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_token")
public class TokenEntity extends BaseProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 100)
    private String email;

    @Column
    private Long user_id;

    @Column(length = 50)
    private String token;

    @Column
    private Date expired_on;

    @Column
    private Boolean is_expired;

    @Column(length = 20)
    private String used_for;

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

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpired_on() {
        return this.expired_on;
    }

    public void setExpired_on(Date expired_on) {
        this.expired_on = expired_on;
    }

    public Boolean isIs_expired() {
        return this.is_expired;
    }

    public Boolean getIs_expired() {
        return this.is_expired;
    }

    public void setIs_expired(Boolean is_expired) {
        this.is_expired = is_expired;
    }

    public String getUsed_for() {
        return this.used_for;
    }

    public void setUsed_for(String used_for) {
        this.used_for = used_for;
    }

}
