package com.scentheartsstudio.scentheartsstudio.jwt;


import com.scentheartsstudio.scentheartsstudio.entities.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.repositories.UserRepository;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = ur.getUserByEmail(email);
        if (userEntity == null){
            throw new UsernameNotFoundException("User not found with Email " + email);
        }
        return new UserPrincipal(userEntity, ur);
    }
}
