package com.scentheartsstudio.scentheartsstudio.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.DTO.LoginDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    //Login
    public LoginDTO loginService(String email, String password) throws CustomException {
        UserEntity dataUser = ur.getEmailAndPasswordUser(email, password);

        if (dataUser != null) {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUser_id(dataUser.getId());

            return loginDTO;
        } else {
            throw new CustomException(452, "Email atau Password Salah!");
        }
    }

}
