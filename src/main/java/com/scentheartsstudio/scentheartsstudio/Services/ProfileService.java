package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProfileDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProfileDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.BiodataEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.BiodataRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.TokenRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
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


/*	sendOTP
	validasi change old email to new email
	change email should be new email
	if already change email
	and wanna change email but using old email
	it can't*/

}