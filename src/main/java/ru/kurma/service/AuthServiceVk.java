package ru.kurma.service;

import org.springframework.stereotype.Service;
import ru.kurma.model.Role;

import java.util.Set;

@Service
public class AuthServiceVk implements AuthService {
    @Override
    public String buildUrl() {
        return null;
    }

    @Override
    public void auth(String code) {

    }

    @Override
    public void login(String user, String pass, Set<Role> roles) {

    }
}
