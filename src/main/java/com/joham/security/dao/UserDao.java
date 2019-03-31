package com.joham.security.dao;


import com.joham.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author joham
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
