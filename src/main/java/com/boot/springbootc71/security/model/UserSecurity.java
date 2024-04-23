package com.boot.springbootc71.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity(name = "user_security")
public class UserSecurity {
    @Id
    @SequenceGenerator(name = "secSeqGen", sequenceName = "user_security_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "secSeqGen")
    private Long id;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_blocked")
    private Boolean isBlocked;
}
