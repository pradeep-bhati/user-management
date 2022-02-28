package com.iqvia.myapplication.security.configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.iqvia.myapplication.entites.User;

public class CurrentUser implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5919977821400241524L;
	private User user;
	
	public  CurrentUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoles())).collect(Collectors.toList());
		return simpleGrantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
