package com.ne.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ne.model.User;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String names;

	@JsonIgnore
	private String email;
	
	private String phoneNumber;
	
	private String nationalId;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private String pin;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String names, String email,String phoneNumber, String nationalId, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.names = names;
		this.nationalId = nationalId;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserPrincipal(user.getId(), user.getNames(), user.getEmail(), user.getPhoneNumber(),
				user.getNationalId(), user.getPassword(), authorities);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return names;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return phoneNumber;
	}

	@Override
	public String getUsername() {
		return nationalId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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
