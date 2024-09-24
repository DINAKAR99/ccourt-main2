package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Role;
import com.example.demo.model.ServiceMaster;
import com.example.demo.model.User;

import java.util.*;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;
    private List<ServiceMaster> serviceMasters;

    public List<ServiceMaster> getServiceMasters() {
        return serviceMasters;
    }

    public void setServiceMasters(List<ServiceMaster> serviceMasters) {
        this.serviceMasters = serviceMasters;
    }

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CustomUserDetails(User user, List<ServiceMaster> serviceMasters, Role role) {
        super();
        this.serviceMasters = serviceMasters;
        this.user = user;
        this.role = role;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
