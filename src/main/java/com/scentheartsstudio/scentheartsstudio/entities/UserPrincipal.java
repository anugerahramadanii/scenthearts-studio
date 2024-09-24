package com.scentheartsstudio.scentheartsstudio.entities;

import com.scentheartsstudio.scentheartsstudio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

	private UserEntity userEntity;
	private UserRepository userRepository;

	public UserPrincipal(UserEntity userEntity, UserRepository userRepository) {
		this.userEntity = userEntity;
		this.userRepository = userRepository;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

		String role = userRepository.getRoleByUserId(userEntity.getId());
//		System.out.println("Role fetched: " + role);

		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
