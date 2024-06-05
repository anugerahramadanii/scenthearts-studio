package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProfileDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProfileDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.BiodataEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.TokenEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.BiodataRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.TokenRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class ProfileService {
	@Autowired
	private BiodataRepository br;

	@Autowired
	private UserRepository ur;

	@Autowired
	private TokenRepository tr;

	@Autowired
	private EmailService es;

	public InterProfileDTO getProfileByUserId(Long user_id) {
		return br.getProfileByUserId(user_id);
	}

	public void updateProfile(PostProfileDTO postProfileDTO){
		UserEntity userEntity = ur.getReferenceById(postProfileDTO.getUser_id());
		userEntity.setModified_by(postProfileDTO.getUser_id());
		userEntity.setModified_on(new Date());
		ur.save(userEntity);

		BiodataEntity biodataEntity = br.getReferenceById(userEntity.getBiodata_id());
		biodataEntity.setFirstname(postProfileDTO.getFirstname());
		biodataEntity.setLastname(postProfileDTO.getLastname());
		biodataEntity.setMobile_phone(postProfileDTO.getMobile_phone());
		biodataEntity.setModified_by(postProfileDTO.getUser_id());
		biodataEntity.setModified_on(new Date());
		br.save(biodataEntity);
	}

	public void changeEmail(PostProfileDTO postProfileDTO) throws CustomException {
		// check OTP
		Boolean isTokenCorrect = tr.isTokenCorrect(postProfileDTO.getEmail(), postProfileDTO.getOtp());
		if (!isTokenCorrect) {
			throw new CustomException(453, "Code OTP Wrong");
		}

		Boolean isTokenExpired = tr.isTokenExpired(postProfileDTO.getEmail());
		if (isTokenExpired) {
			throw new CustomException(454, "Code OTP Expired");
		}

		UserEntity userEntity = ur.getReferenceById(postProfileDTO.getUser_id());
		userEntity.setEmail(postProfileDTO.getEmail());
		userEntity.setModified_by(postProfileDTO.getUser_id());
		userEntity.setModified_on(new Date());
		ur.save(userEntity);
	}

	public void sendEmailOTPProfile(String email) throws CustomException {
		// validasi email
		Boolean isEmailExist = ur.isEmailExists(email);
		if (isEmailExist) {
			throw new CustomException(451, "Email already exists!!");
		}
			// Generate OTP token
			String token = Utilities.generateToken6Digit();
			String usedFor = "Change Email";
			Date expiredOn = new Date(System.currentTimeMillis() + 1000 * 60 * 5);

			// Simpan data ke table token
			TokenEntity tokenEntity = new TokenEntity();
			tokenEntity.setEmail(email);
			tokenEntity.setToken(token);
			tokenEntity.setUsed_for(usedFor);
			tokenEntity.setExpired_on(expiredOn);
			tokenEntity.setCreated_by(1L);
			tokenEntity.setCreated_on(new Date());
			tr.save(tokenEntity);

			// Sent Email
			String subject = "Change Email";
			String message = "Token OTP anda adalah " + token + " ! Jangan beritahukan ke siapa-siapa!";
			es.sendEmail(email, subject, message);
		}

	public void changePassword(PostProfileDTO postProfileDTO) throws CustomException {
		//validasi password
		// take user id
		UserEntity userEntity = ur.getReferenceById(postProfileDTO.getUser_id());
		if (!postProfileDTO.getOld_password().equals(userEntity.getPassword())){
			throw new CustomException(454, "Old Password Wrong!!");
		}
		//Update new password
		userEntity.setPassword(postProfileDTO.getNew_password());
		userEntity.setModified_by(postProfileDTO.getUser_id());
		userEntity.setModified_on(new Date());
		ur.save(userEntity);
	}


}