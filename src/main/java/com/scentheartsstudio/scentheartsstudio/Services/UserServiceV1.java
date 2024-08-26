//package com.scentheartsstudio.scentheartsstudio.Services;
//
//import com.scentheartsstudio.scentheartsstudio.DTO.LoginDTO;
//import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
//import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
//import com.scentheartsstudio.scentheartsstudio.jwt.JwtTokenUtil;
//import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceV1 {
//
//    @Autowired
//    private UserRepository ur;
//
//    @Autowired
//    private AuthenticationManager authManager;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    //Login
//    public LoginDTO loginService(String email, String password) throws CustomException {
//        UserEntity dataUser = ur.getEmailAndPasswordUser(email, password);
//
//        if (dataUser != null) {
//            LoginDTO loginDTO = new LoginDTO();
//            loginDTO.setUser_id(dataUser.getId());
//
//            Authentication auth = authManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(dataUser.getEmail(), dataUser.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            UserDetails userDetails = (UserDetails) auth.getPrincipal();
//            String token = jwtTokenUtil.generateToken(userDetails);
//
//            loginDTO.setToken(token);
//
//            return loginDTO;
//        } else {
//            throw new CustomException(452, "Email atau Password Salah!");
//        }
//    }
//}
//
//
//
