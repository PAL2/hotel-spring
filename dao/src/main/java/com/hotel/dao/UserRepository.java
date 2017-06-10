package com.hotel.dao;

import com.hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}