package com.ra.security_api.repository;

import com.ra.security_api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByUsername(String username);

}
