package com.scentheartsstudio.scentheartsstudio.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String firstname;
    private String lastname;
    private String mobile_phone;
    private Long role_id;
    private String password;
    private String email;
    private String otp;
}
