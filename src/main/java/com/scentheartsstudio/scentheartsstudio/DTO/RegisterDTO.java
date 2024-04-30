package com.scentheartsstudio.scentheartsstudio.DTO;

public class RegisterDTO {
    private String firstname;
    private String lastname;
    private String mobile_phone;
    private Long role_id;
    private String password;
    private String email;
    private String otp;

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

    public Long getRole_id() {
        return this.role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
