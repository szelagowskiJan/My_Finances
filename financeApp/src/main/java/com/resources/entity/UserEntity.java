package com.resources.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Praca
 */
@Getter
@Setter
@Entity
@Table(name = "USERS")
@Data
public class UserEntity {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int balance;

    @Column(name = "USER_ROLE", nullable = false)
    private int userRole;

    @Column(name = "VERIFICATION_CODE", length = 64)
    private String verificationCode;

    @Column(name = "ENABLED")
    private boolean enabled;

    @OneToMany()
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private List<Product> product = new ArrayList<>();
}
