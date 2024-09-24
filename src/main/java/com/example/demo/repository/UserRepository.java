package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    public User findByUserId(String userName);

    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.userCode = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, Long userCode);

}