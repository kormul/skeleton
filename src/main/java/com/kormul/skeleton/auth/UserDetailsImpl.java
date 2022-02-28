package com.kormul.skeleton.auth;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kormul.skeleton.domain.User;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails{

	private String username;
	
	private String password;
	
	private List<GrantedAuthority> authorities;
	
	
	public UserDetailsImpl(User user) {
		username = user.getUsername();
		password = user.getPassword();
		authorities = Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	@Override
	public List<GrantedAuthority> getAuthorities() {
		System.out.println(authorities.get(0));
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
