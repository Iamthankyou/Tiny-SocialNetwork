package com.devteam.social_network.repos;

import java.util.Optional;

import com.devteam.social_network.domain.ERole;
import com.devteam.social_network.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
