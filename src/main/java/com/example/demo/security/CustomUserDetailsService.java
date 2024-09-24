package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import com.example.demo.model.Role;
import com.example.demo.model.ServiceMaster;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.ServiceMasterRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleMapEntityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRoleMapEntityRepository userRoleMapEntityRepository;

    Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private ServiceMasterRepository serviceMasterRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);
        Role role = null;
        List<ServiceMaster> serviceMasters = null;

        if (user != null && user.isAccountNonLocked()) {
            Long roleId = userRoleMapEntityRepository.findByUserCode(user.getUserCode()).getRoleId();
            role = roleRepository.findByRoleId(roleId);
            serviceMasters = serviceMasterRepository.getAllServicesByRole(roleId).stream()
                    .filter(serviceMaster -> serviceMaster.getDeleteFlag().equals("F")).collect(Collectors.toList());

        }

        return new CustomUserDetails(user, serviceMasters, role);
    }

}
