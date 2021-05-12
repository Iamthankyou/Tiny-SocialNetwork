package com.devteam.social_network.security.services;

import com.devteam.social_network.domain.User;
import com.devteam.social_network.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByNickName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
		User customer = userRepository.findByEmail(email);
		if (customer != null) {
			customer.setResetPasswordToken(token);
			userRepository.save(customer);
		} else {
			throw new UsernameNotFoundException("Could not find any customer with the email " + email);
		}
	}


}
