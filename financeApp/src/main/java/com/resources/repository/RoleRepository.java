package com.resources.repository;

import com.resources.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(int repeId);
}
