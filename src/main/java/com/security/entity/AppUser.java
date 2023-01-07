package com.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.security.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(AppUser.class);

    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType roleType;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    private String password;



}
