package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.dto.LoginDTO;
import com.scentheartsstudio.scentheartsstudio.entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.repositories.UserRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Login
    public LoginDTO loginService(String email, String password) throws CustomException {
        UserEntity dataUser = ur.getUserByEmail(email); // Fetch user by email only

        if (dataUser == null || !passwordEncoder.matches(password, dataUser.getPassword())) {
            throw new CustomException(401, "Email atau Password Salah!");
        }

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser_id(dataUser.getId());
        loginDTO.setToken(token);
        return loginDTO;
    }
}



