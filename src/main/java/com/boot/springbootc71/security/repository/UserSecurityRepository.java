package com.boot.springbootc71.security.repository;

import com.boot.springbootc71.security.model.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUserLogin(String userLogin);
}
