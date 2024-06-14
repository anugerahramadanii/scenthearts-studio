package com.scentheartsstudio.scentheartsstudio.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.DTO.RegisterDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.AdminEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.BiodataEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.CustomerEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.TokenEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.AdminRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.BiodataRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.CustomerRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.TokenRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Utilities;

@Service
public class RegisterService {
    @Autowired
    private UserRepository ur;

    @Autowired
    private TokenRepository tr;

    @Autowired
    private BiodataRepository br;

    @Autowired
    private AdminRepository ar;

    @Autowired
    private CustomerRepository cr;

    @Autowired
    private EmailService es;

    public void registerUser(RegisterDTO registerDTO) throws CustomException {

        Boolean isTokenCorrect = tr.isTokenCorrect(registerDTO.getEmail(), registerDTO.getOtp());
        if (!isTokenCorrect) {
            throw new CustomException(452, "Code OTP Wrong!!!");
        }

        Boolean isTokenExpired = tr.isTokenExpired(registerDTO.getEmail());
        if (isTokenExpired) {
            throw new CustomException(453, "Code OTP Expired!!!");
        }

        String passwordUser = registerDTO.getPassword();
        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        if (!passwordUser.matches(regexPassword)){
            throw new CustomException(455, "Minimal 8 karakter, Setidaknya satu huruf besar, Setidaknya satu huruf kecil, Setidaknya satu digit, Setidaknya satu karakter spesial");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPassword(passwordUser);
        userEntity.setCreated_by(1L);
        userEntity.setCreated_on(new Date());
        userEntity.setRole_id(2L); //Customer

        userEntity = ur.save(userEntity);

        BiodataEntity biodataEntity = new BiodataEntity();
        biodataEntity.setFirstname(registerDTO.getFirstname());
        biodataEntity.setLastname(registerDTO.getLastname());
        biodataEntity.setMobile_phone(registerDTO.getMobile_phone());
        biodataEntity.setCreated_by(1L);
        biodataEntity.setCreated_on(new Date());

        biodataEntity = br.save(biodataEntity);

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setBiodata_id(biodataEntity.getId());
        customerEntity.setCreated_by(1L);
        customerEntity.setCreated_on(new Date());

        cr.save(customerEntity);

        // update biodata.id di table m_user
        userEntity.setBiodata_id(biodataEntity.getId());

        ur.save(userEntity);
    }

    public void sendRegisterEmailOTP(String email) throws CustomException {
        Boolean isEmailExists = ur.isEmailExists(email);
        if (isEmailExists) {
            throw new CustomException(452, "Email has already registered!");
        }

        String token = Utilities.generateToken6Digit();
        String usedFor = "Register Account";
        Date expiredOn = new Date(System.currentTimeMillis() + (1000 * 60 * 5));

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setEmail(email);
        tokenEntity.setUser_id(2L);
        tokenEntity.setExpired_on(expiredOn);
        tokenEntity.setToken(token);
        tokenEntity.setUsed_for(usedFor);
        tokenEntity.setCreated_by(1L);
        tokenEntity.setCreated_on(new Date());

        tr.save(tokenEntity);

        String subject = "Register OTP";
        String msgBody = "Token OTP anda adalah " + token + " ! Jangan beritahukan ke siapa-siapa!" +
                "Token expired in " + expiredOn;
        es.sendEmail(email, subject, msgBody);
    }

}
