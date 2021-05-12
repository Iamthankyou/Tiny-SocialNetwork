package com.devteam.social_network.repos;

import java.util.Optional;

import com.devteam.social_network.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByNickName(String username);
	Boolean existsByNickName(String username);
	User findByEmail(String email);
	User findByResetPasswordToken(String token);
	Boolean existsByEmail(String email);
}
