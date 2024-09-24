package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  public Role findByRoleId(Long roleId);

  List<Role> findAllByDeleteFlagOrderByRoleIdAsc(char deleteFlag);
}
