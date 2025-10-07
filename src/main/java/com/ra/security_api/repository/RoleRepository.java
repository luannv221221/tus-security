package com.ra.security_api.repository;

import com.ra.security_api.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
