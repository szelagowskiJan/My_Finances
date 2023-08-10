package com.resources.details.impl;

import com.resources.entity.Role;
import com.resources.entity.UserEntity;
import com.resources.repository.RoleRepository;
import org.springframework.security.core.userdetails.User;
import com.resources.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = repo.findByEmail(email);
        if (userEntity != null) {
            if (!userEntity.isEnabled()) {
                throw new UsernameNotFoundException("User with username: " + email + " is not active");
            }
            Role role = roleRepository.findById(userEntity.getUserRole());
            return new User(userEntity.getEmail(), userEntity.getPassword(), buildSimpleGrantedAuthorities(role));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(Role role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return authorities;
    }
}
