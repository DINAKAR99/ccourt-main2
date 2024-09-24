package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserRoleMapEntity;

@Repository
public interface UserRoleMapEntityRepository extends JpaRepository<UserRoleMapEntity, Long> {
	UserRoleMapEntity findByUserCode(Long userCode);
}
