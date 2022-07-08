package com.ne.controllers;

import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ne.dto.LoginRequest;
import com.ne.dto.SignUpRequest;
import com.ne.model.Role;
import com.ne.model.User;
import com.ne.model.enums.ERoleName;
import com.ne.repositories.RoleRepository;
import com.ne.repositories.UserRepository;
import com.ne.security.JwtTokenProvider;
import com.ne.utils.APIResponse;
import com.ne.utils.EStatus;
import com.ne.utils.JwtAuthenticationResponse;

@RestController
@RequestMapping("/users")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			Optional<User> findByEmail = userRepository.findByEmailOrPhoneNumber(loginRequest.getLogin(),loginRequest.getLogin());
			if(findByEmail.isEmpty()) {
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown account");
			}
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken(authentication);
			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		}catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(EStatus.SUCCESS,e.getLocalizedMessage()));
		}
		
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		try {
			if (signUpRequest.getEmail() != null && userRepository.existsByEmail(signUpRequest.getEmail())) {
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Address already in use!");
			}

			if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number already in use!");
			}
			
			User user = new User(signUpRequest.getNames(), signUpRequest.getEmail(), signUpRequest.getPhoneNumber(),
					signUpRequest.getNationalId(), signUpRequest.getPassword());
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			Optional<Role> userRole = roleRepository.findByName(ERoleName.ROLE_USER);
		

			user.setRoles(Collections.singleton(userRole.get()));

			User result = userRepository.save(user);

			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(new APIResponse(EStatus.SUCCESS,result));
		}catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(EStatus.SUCCESS,e.getLocalizedMessage()));
		}
	}

	

}
