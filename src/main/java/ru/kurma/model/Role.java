package ru.kurma.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    public Role(String role) {
        this.role = role;
    }

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
