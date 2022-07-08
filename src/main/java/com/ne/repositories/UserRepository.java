package com.ne.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ne.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String usernameOrPhoneOrEmail);

	Optional<User> findByEmailOrPhoneNumber(String login, String login2);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String mobile);

}
