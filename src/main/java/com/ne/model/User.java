package com.ne.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Names are required")
	private String names;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull(message = "Phone Number is required")
	private String phoneNumber;
	
	@NotNull(message = "National Id is required")
	private String nationalId;

	@JsonIgnore
	@NotNull
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(Long id, @NotNull(message = "Names are required") String names, @NotNull @Email String email,
			@NotNull(message = "Phone Number is required") String phoneNumber,
			@NotNull(message = "National Id is required") String nationalId, @NotNull String password) {
		super();
		this.id = id;
		this.names = names;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nationalId = nationalId;
		this.password = password;
	}

	public User(@NotNull(message = "Names are required") String names, @NotNull @Email String email,
			@NotNull(message = "Phone Number is required") String phoneNumber,
			@NotNull(message = "National Id is required") String nationalId, @NotNull String password) {
		super();
		this.names = names;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.nationalId = nationalId;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
