package com.security.auth_rb.jpa.repository;

import com.security.auth_rb.enums.Role;
import com.security.auth_rb.jpa.entities.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);

    User findByUserRole(Role role);
}
