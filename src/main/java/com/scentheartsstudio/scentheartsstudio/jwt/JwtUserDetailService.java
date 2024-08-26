package com.scentheartsstudio.scentheartsstudio.jwt;

import java.util.ArrayList;
import java.util.List;

import com.scentheartsstudio.scentheartsstudio.Entities.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;

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

//        System.out.println("Loaded User: " + userEntity.getEmail() + ", Password: " + userEntity.getPassword());

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole_id()));

//        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        return new UserPrincipal(userEntity);
    }


}
