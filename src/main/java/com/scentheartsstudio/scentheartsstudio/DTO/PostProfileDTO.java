package com.scentheartsstudio.scentheartsstudio.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostProfileDTO {
	private Long user_id;
	private String firstname;
	private String lastname;
	private String mobile_phone;
	private String email;
	private String otp;
	private String old_password;
	private String new_password;
}
