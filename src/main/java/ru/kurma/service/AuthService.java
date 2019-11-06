package ru.kurma.service;

import ru.kurma.model.Role;

import java.util.Set;

public interface AuthService {

    String buildUrl();
    void auth(String code);
    void login(String user, String pass, Set<Role> roles);
}
